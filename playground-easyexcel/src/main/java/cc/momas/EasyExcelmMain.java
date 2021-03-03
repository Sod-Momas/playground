package cc.momas;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

public class EasyExcelmMain {
    static Logger log = LoggerFactory.getLogger(EasyExcelmMain.class);

    public static void main(String[] args) throws IOException {
        String jdkVersion = System.getProperty("java.version");
        // 由于cglib不再支持新版本jdk，所以只能要求jdk8
        if (!jdkVersion.contains("1.8")) {
            throw new RemoteException("easyexcel要求jdk 1.8,当前jdk版本为：" + jdkVersion);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        log.info("写入excel开始");
        writeExcel(out);
        log.info("写入excel完成");
        InputStream in = new ByteArrayInputStream(out.toByteArray());
        log.info("读取excel开始");
        readExcel(in);
        log.info("读取excel结束");
    }

    private static void readExcel(InputStream in) {
        final SyncReadListener listener = new SyncReadListener();
        EasyExcel
//                .read("playground-easyexcel/src/main/resources/订单示例.xlsx")
                .read(in)
                .head(Order.class)
                .registerReadListener(listener)
                .sheet()
                .doRead();
        final List<Object> list = listener.getList();
        log.info("从excel中读取的数据={}", list);
    }

    private static void writeExcel(OutputStream out) {
        final List<Order> dataList = Arrays.asList(OrderFactory.getOrder(), OrderFactory.getOrder());
        log.info("用于写入excel的数据={}", dataList);
        try {
            // 创建临时文件
//            final Path tempFile = Files.createTempFile("playground-easyexcel", ".xlsx");
            // 把数据写成excel格式
            final ExcelWriter excelWriter = new ExcelWriterBuilder()
//                    .file(tempFile.toFile())
                    .file(out)
                    .excelType(ExcelTypeEnum.XLSX)
                    .build();
            final WriteSheet sheet = new ExcelWriterSheetBuilder()
                    .head(Order.class)
                    .build();
            excelWriter.write(dataList, sheet);
            excelWriter.finish();
            // 自动打开创建的excel文件
//            Runtime.getRuntime().exec("explorer " + tempFile.toAbsolutePath().toString());

            // 旧API
            // 保存到内存
//        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        // 把数据写成excel格式
//        final ExcelWriter excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
//        final Sheet sheet = new Sheet(1, 0, order.getClass());
//        sheet.setSheetName("order");
//        excelWriter.write(Arrays.asList(order, order), sheet);
//        excelWriter.finish();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
