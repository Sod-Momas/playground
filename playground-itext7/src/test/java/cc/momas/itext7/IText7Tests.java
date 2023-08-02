package cc.momas.itext7;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.TextRenderer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Sod-Momas
 * @since 2023/8/2
 */
public class IText7Tests {

    private static final String BASE_DIR = "./output";
    private static final Path BASE_PATH = Paths.get(BASE_DIR);
    private static final Logger log = LoggerFactory.getLogger(IText7Tests.class);

    @Test
    public void baseDir() throws IOException {
        if (Files.notExists(BASE_PATH)) {
            Files.createDirectories(BASE_PATH);
        }
        System.out.println(BASE_PATH.toAbsolutePath());
    }

    @Test
    public void createBlankPdf() throws IOException {
        // 创建一个空白pdf
        String filename = "blank" + System.currentTimeMillis() + ".pdf";
        PdfWriter writer = new PdfWriter(BASE_PATH.resolve(filename).toFile());
        PdfDocument pdf = new PdfDocument(writer);
        // 添加空白页
        pdf.addNewPage();
        pdf.close();
    }

    @Test
    public void createCover() throws IOException {
        // 创建一个封面
        String filename = "cover" + System.currentTimeMillis() + ".pdf";
        PdfWriter writer = new PdfWriter(BASE_PATH.resolve(filename).toFile());
        PdfDocument pdf = new PdfDocument(writer);
        pdf.setDefaultPageSize(PageSize.A2);
        // 添加封面图片
        addCover(pdf);
        // 添加标题
        addCoverTitle(pdf);
        // 添加日期署名
        addDate(pdf);
        pdf.close();
    }

    @Test
    public void createCoverPdf() throws IOException {
//        Path tmp = Files.createTempFile("tmp", ".pdf");
//        log.info("{}", tmp.toAbsolutePath());
//        File file = tmp.toFile();
//        PdfDocument pdf = new PdfDocument(new PdfWriter(file));

        // 创建一个封面
        String filename = "coverpdf" + System.currentTimeMillis() + ".pdf";
        PdfWriter writer = new PdfWriter(BASE_PATH.resolve(filename).toFile());
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);
//
        // @see https://blog.csdn.net/wozhaoying/article/details/112171059 itext创建图片背景
        // 记住旧margin
        float tm = doc.getTopMargin();
        float lm = doc.getLeftMargin();
        float rm = doc.getRightMargin();
        float bm = doc.getBottomMargin();
        // 清除margin
        doc.setMargins(0F, 0F, 0F, 0F);

        // 设置封面
        URL coverPng = getRes("img/封面.png");
        if (coverPng != null) {
            PageSize ps = pdf.getDefaultPageSize();
            float width = ps.getWidth();
            float height = ps.getHeight();

            ImageData data = ImageDataFactory.create(coverPng);
            Image image = new Image(data);
            image.scaleToFit(width, 652);
            image.setFixedPosition(1, 0, height - image.getImageScaledHeight());
            doc.add(image);
        } else {
            log.error("封面找不到");
        }

        // 恢复margin
        doc.setMargins(tm, rm, bm, lm);

        // 设置标题
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(getFont());
        paragraph.setFontSize(56F);
        paragraph.setBold();
        paragraph.setMarginTop(360F);
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.add("这是标题");

        Div div = new Div();
        div.add(paragraph);
        doc.add(div);

        // 样式参考自Figma
        Style style = new Style();
        style.setFontSize(28F);
        style.setFont(getFont());
        style.setFixedPosition(754F, 228F, 316F);
        style.setTextAlignment(TextAlignment.RIGHT);
        Div date = new Div();
        date.add(new Paragraph().add("日期：" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/M/d"))));
        date.add(new Paragraph().add("莫莫砂科技 - 情报中心"));
        date.addStyle(style);
        doc.add(date);
        doc.flush();
        doc.close();
    }

    @Test
    public void addPageFoot() throws IOException {
        // 创建一个封面
        String filename = "pagefoot" + System.currentTimeMillis() + ".pdf";
        PdfWriter writer = new PdfWriter(BASE_PATH.resolve(filename).toFile());
        PdfDocument pdf = new PdfDocument(writer);
        pdf.setDefaultPageSize(PageSize.A2);
        // 添加页脚
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new AddPageFootHandler());
        // 添加封面图片
        addCover(pdf);
        // 添加标题
        addCoverTitle(pdf);
        // 添加日期署名
        addDate(pdf);
        pdf.close();
    }

    @Test
    public void addBaseInfo() throws IOException, URISyntaxException {
        // 创建一个封面
        String filename = "baseinfo" + System.currentTimeMillis() + ".pdf";
        PdfWriter writer = new PdfWriter(BASE_PATH.resolve(filename).toFile());
        PdfDocument pdf = new PdfDocument(writer);
        pdf.setDefaultPageSize(PageSize.A2);
        // 添加页脚
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new AddPageFootHandler());
        // 添加封面图片
        addCover(pdf);
        // 添加标题
        addCoverTitle(pdf);
        // 添加日期署名
        addDate(pdf);
        // 添加基础信息
        addBaseInfo(pdf);
        pdf.close();
    }

    @Test
    public void addPageHeader() throws IOException, URISyntaxException {
        // 创建一个封面
        String filename = "pageheader" + System.currentTimeMillis() + ".pdf";
        PdfWriter writer = new PdfWriter(BASE_PATH.resolve(filename).toFile());
        PdfDocument pdf = new PdfDocument(writer);
        pdf.setDefaultPageSize(PageSize.A2);
        // 添加页脚
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new AddPageFootHandler());
        // 添加页眉
        pdf.addEventHandler(PdfDocumentEvent.INSERT_PAGE, new AddPageHeaderHandler());
//        new Document(pdf).setTopMargin(80F);
        // 添加封面图片
        addCover(pdf);
        // 添加标题
        addCoverTitle(pdf);
        // 添加日期署名
        addDate(pdf);
        // 添加基础信息
        addBaseInfo(pdf);
        pdf.close();
    }

    @Test
    public void addTableOfContent() throws IOException, URISyntaxException {
        // 创建一个封面
        String filename = "toc" + System.currentTimeMillis() + ".pdf";
        PdfWriter writer = new PdfWriter(BASE_PATH.resolve(filename).toFile());
        PdfDocument pdf = new PdfDocument(writer);
        pdf.setDefaultPageSize(PageSize.A2);
        // 添加页脚
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new AddPageFootHandler());
        // 添加页眉
        pdf.addEventHandler(PdfDocumentEvent.INSERT_PAGE, new AddPageHeaderHandler());
        // 添加封面图片
        addCover(pdf);
        // 添加标题
        addCoverTitle(pdf);
        // 添加日期署名
        addDate(pdf);
        // 添加基础信息
        addBaseInfo(pdf);
        // 添加目录
        addTableOfContent(pdf);
        pdf.close();
    }

    private void addTableOfContent(PdfDocument pdf) {
        // 目录添加到第2页
        int pageNumber = 2;
        Document doc = new Document(pdf);
        PdfPage page = pdf.addNewPage(pageNumber);
        URL toc = getClass().getClassLoader().getResource("img/目录.png");
        Div div = new Div();
        if (toc == null) {
            log.error("目录找不到");
        } else {
            // 目录图片
            ImageData data = ImageDataFactory.create(toc);
            Image image = new Image(data);
            div.add(image);
        }
        // 用于做书签使用
//        final PdfOutline root = pdf.getOutlines(true);
//        int jumpTo = 1;
//        for (final String title : Arrays.asList("基础信息", "可利用性", "相关编号", "详情信息", "修复方案", "参考链接")) {
////            final PdfPage page = pdf.addNewPage();
////            System.out.println("page *****" + page + " ** " + page.getPdfObject());
//            // 用于点击目录条目做跳转的处理，类始于锚点定位的功能
//            final String destinationKey = "p" + pdf.getNumberOfPages();
//            final PdfArray destinationArray = new PdfArray();
//            destinationArray.add(page.getPdfObject());
//            destinationArray.add(PdfName.XYZ);
//            destinationArray.add(new PdfNumber(0));
//            destinationArray.add(new PdfNumber(page.getMediaBox().getHeight()));
//            destinationArray.add(new PdfNumber(1));
//            pdf.addNamedDestination(destinationKey, destinationArray);
//            // 书签
//            final Text text = new Text(title);
//            text.setFont(getFont());
//            text.setNextRenderer(new TOCTextRenderer(root, text));
//            // 生成目录
//            final Paragraph p = new Paragraph();
//            p.setFont(getFont());
//            p.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));
//            p.add(text);
//            p.add(new Tab());
//            p.add(String.valueOf(pdf.getNumberOfPages() - jumpTo));
//            p.setProperty(Property.ACTION, PdfAction.createGoTo(destinationKey));
//            jumpTo++;
//
//            div.add(p);
//        }
        div.setFixedPosition(pageNumber, 0, 1330, pdf.getDefaultPageSize().getWidth());
        doc.add(div);
    }

    private void addBaseInfo(PdfDocument pdf) throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource("push_task_pdf_template.html");
        Objects.requireNonNull(url, "html not found");
        Path tmp = Files.createTempFile("baseinfotmp", ".pdf");
//        tmp.toFile().deleteOnExit();
        try (InputStream is = Files.newInputStream(Paths.get(url.toURI()))) {

            URL fontPath = IText7Tests.class.getClassLoader().getResource("font");

            FontProvider provider = new FontProvider();
//            provider.addSystemFonts();
//            provider.addStandardPdfFonts();
            if (fontPath != null) {
                provider.addDirectory(fontPath.getPath());
            }

            ConverterProperties props = new ConverterProperties();
            props.setFontProvider(provider);

            // html转为pdf文件
            PdfDocument tmpPdf = new PdfDocument(new PdfWriter(tmp.toFile()));
            tmpPdf.setDefaultPageSize(pdf.getDefaultPageSize());
            HtmlConverter.convertToPdf(is, tmpPdf, props);
        }

        try (PdfDocument doc = new PdfDocument(new PdfReader(tmp.toFile()))) {
            for (int i = 1; i <= doc.getNumberOfPages(); i++) {
                // 把临时生成的pdf拼接到原来的pdf上
                PdfPage copy = doc.getPage(i).copyTo(pdf);
                pdf.addPage(copy);
            }
        } finally {
//            // 清除临时文件
            Files.deleteIfExists(tmp);
        }
    }

    public static class AddPageFootHandler implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            if (!(event instanceof PdfDocumentEvent)) return;
            // @see https://blog.csdn.net/zhuiyue82/article/details/115681421
            final PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            final PdfDocument pdfDoc = docEvent.getDocument();
            final PdfPage page = docEvent.getPage();
//            final int pageNumber = pdfDoc.getPageNumber(page);
//            if (pageNumber == 1) return;
            final Rectangle pageSize = page.getPageSize();

            Paragraph line = new Paragraph("Copyright © 上海莫莫砂信息科技有限公司 2023 All Rights Reserved");
            line.setFont(IText7Tests.getFont());
            line.setFontSize(19.6F);
            line.setFontColor(new DeviceRgb(135, 135, 135));
            line.setTextAlignment(TextAlignment.CENTER);
            line.setFixedPosition(0, 48, pageSize.getWidth());

            new Document(pdfDoc).add(line);
        }
    }

    private void addDate(PdfDocument pdf) {
        PdfFont font = getFont();

        // 样式参考自Figma
        Style style = new Style();
        style.setFontSize(28F);
        style.setFont(font);
        style.setFixedPosition(754F, 228F, 316F);
        style.setTextAlignment(TextAlignment.RIGHT);

        Div foot = new Div();
        foot.add(new Paragraph().add("日期：" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/M/d"))));
        foot.add(new Paragraph().add("莫莫砂科技 - 情报中心"));
        foot.addStyle(style);

        new Document(pdf).add(foot);
    }

    private void addCoverTitle(PdfDocument pdf) {
        Paragraph paragraph = new Paragraph();
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.add("这个是标题这是一个很长的标题-这个是标题这是一个很长的标题-这个是标题这是一个很长的标题-这个是标题这是一个很长的标题");
        // 中文字体
        PdfFont font = getFont();

        Style style = new Style();
        style.setFontSize(56F);
        style.setBold();
        style.setFont(font);
        style.setMarginTop(360F);

        Div middle = new Div();
        middle.addStyle(style);
        middle.add(paragraph);

        new Document(pdf).add(middle);
    }

    @Test
    public void openBaseDir() throws IOException {
        Runtime.getRuntime().exec("explorer " + BASE_PATH.toAbsolutePath());
    }

    public static class AddPageHeaderHandler implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            if (!(event instanceof PdfDocumentEvent)) return;
            // @see https://blog.csdn.net/zhuiyue82/article/details/115681421
            final PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            final PdfDocument pdfDoc = docEvent.getDocument();
            final PdfPage page = docEvent.getPage();
            int pageNumber = pdfDoc.getPageNumber(page);
            // 第一页不需要添加页眉
            if (pageNumber == 1) return;

            URL resource = getClass().getClassLoader().getResource("img/页眉.png");
            if (resource == null) {
                log.error("页眉找不到");
                return;
            }

            final float pdfHeight = page.getPageSize().getHeight();
            ImageData data = ImageDataFactory.create(resource);
            Image image = new Image(data);
            image.setFixedPosition(pageNumber, 0, pdfHeight - image.getImageHeight());

            new Document(pdfDoc).add(image);
        }
    }

    private void addCover(PdfDocument pdf) {
        // @see https://blog.csdn.net/wozhaoying/article/details/112171059 itext创建图片背景
        Document doc = new Document(pdf);
        // 记住旧margin
        float tm = doc.getTopMargin();
        float lm = doc.getLeftMargin();
        float rm = doc.getRightMargin();
        float bm = doc.getBottomMargin();
        // 清除margin
        doc.setMargins(0F, 0F, 0F, 0F);
//        PageSize size = pdf.getDefaultPageSize();
//        float height = size.getHeight();
//        float width = size.getWidth();
        URL resource = getClass().getClassLoader().getResource("img/封面.png");
        if (resource == null) {
            log.error("封面找不到");
            return;
        }
        ImageData data = ImageDataFactory.create(resource);
        Image image = new Image(data);
        doc.add(image);
        // 恢复margin
        doc.setMargins(tm, rm, bm, lm);
    }

    private static PdfFont getFont() {
        try {
            URL fontPath = IText7Tests.class.getClassLoader().getResource("font/SourceHanSansCN-Regular.ttf");
            if (fontPath == null) {
                log.error("字体没有找到");
                throw new FileNotFoundException();
            }
            return PdfFontFactory.createFont(fontPath.getPath(), PdfEncodings.IDENTITY_H, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, Object>> getCatalogList() {
        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        final Map<String, Object> catalogMap = new HashMap<String, Object>();
        catalogMap.put("title", "关于公司");
        catalogMap.put("number", 1);
        list.add(catalogMap);
        final Map<String, Object> catalogMap2 = new HashMap<String, Object>();
        catalogMap2.put("title", "阅读指南");
        catalogMap2.put("number", 2);
        list.add(catalogMap2);
        final Map<String, Object> catalogMap4 = new HashMap<String, Object>();
        catalogMap4.put("title", "测评目的");
        catalogMap4.put("number", 4);
        list.add(catalogMap4);
        final Map<String, Object> catalogMap5 = new HashMap<String, Object>();
        catalogMap5.put("title", "综合评价");
        catalogMap5.put("number", 5);
        list.add(catalogMap5);
        final Map<String, Object> catalogMap6 = new HashMap<String, Object>();
        catalogMap6.put("title", "关于报告");
        catalogMap6.put("number", 6);
        list.add(catalogMap6);
        return list;
    }

    //    作者：半路凉亭
    //    链接：https://juejin.cn/post/7033223985651253262
    //    来源：稀土掘金
    //    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public static class TOCTextRenderer extends TextRenderer {

        protected PdfOutline parentOutline;

        public TOCTextRenderer(final PdfOutline parentOutline, final Text modelElement) {
            super(modelElement);
            this.parentOutline = parentOutline;
        }

        @Override
        public void draw(final DrawContext drawContext) {

            super.draw(drawContext);

            Text t = (Text) this.modelElement;

            Rectangle rect = this.getOccupiedAreaBBox();
            PdfDestination dest = PdfExplicitDestination.createXYZ(drawContext.getDocument().getLastPage(), rect.getLeft(), rect.getTop(), 0);
            PdfOutline curOutline = this.parentOutline.addOutline(t.getText());
            curOutline.addDestination(dest);

            // PdfOutline secOutline = curOutline.addOutline(t.getText() + " - 2");
            // secOutline.addDestination(dest);
            // System.out.println(curOutline.getTitle());
        }

    }
    //    作者：半路凉亭
    //    链接：https://juejin.cn/post/7033223985651253262
    //    来源：稀土掘金
    //    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 从classpath下找资源
     *
     * @param path 资源名，例如 'img/封面.png'
     * @return 资源URL，如果找不到返回null
     */
    private URL getRes(String path) {
        return getClass().getClassLoader().getResource(path);
    }
}
