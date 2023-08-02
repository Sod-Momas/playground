package cc.momas.itext;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;

import java.io.IOException;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        // 输出到这个文件
        final PdfDocument output = new PdfDocument(new PdfWriter("C:\\Users\\sod\\Downloads\\output.pdf"));
        // 输出文件列表
        final PdfDocument first = new PdfDocument(new PdfReader("C:\\Users\\sod\\Downloads\\img-230802100625-001.pdf"));
        final PdfDocument second = new PdfDocument(new PdfReader("C:\\Users\\sod\\Downloads\\img-230802100834-001.pdf"));
        final PdfDocument third = new PdfDocument(new PdfReader("C:\\Users\\sod\\Downloads\\img-230802100907-001.pdf"));
        final PdfDocument forth = new PdfDocument(new PdfReader("C:\\Users\\sod\\Downloads\\img-230802100933-001.pdf"));
        final PdfMerger merge = new PdfMerger(output);
        merge.merge(first, Collections.singletonList(1));
        merge.merge(second, Collections.singletonList(1));
        merge.merge(third, Collections.singletonList(1));
        merge.merge(forth, Collections.singletonList(1));
        // 如果没有这个 close() 缓冲流会不输出，文件会生成失败, 正经来说应该放 finally 里
        merge.close();
    }
}