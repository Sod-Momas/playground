package cc.momas;

import java.util.List;

//import static java.lang.StringTemplate.STR;
//import static java.util.FormatProcessor.FMT;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
        }

        System.out.println(System.getProperty("java.version"));
        // --source 21 --enable-preview true
//        stringTemplate();

        fiber();
    }

    private static void fiber() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Thread fiber = Thread.startVirtualThread(() -> System.out.println("Hello Fiber"));
        }
        long fiberTime = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        for (int j = 0; j < 10000; j++) {
            Thread platform = new Thread(() -> System.out.println("Hello Platform"));
            platform.start();
        }
        long platformTime = System.currentTimeMillis() - start;
        System.out.printf("fiber duration %d ms, platform duration %d ms",fiberTime,platformTime);
    }

//    /**
//     * @link <a href="https://openjdk.org/jeps/430">JEP 430字符串模板</a>
//     */
//    private static void stringTemplate() {
//        // 单行模板
//        String name = "sod";
//        String target = STR."my name is \{name}";
//        System.out.println(target);
//
//        // 多行模板
//        String title = "My Web Page";
//        String text  = "Hello, world";
//        String html = STR."""
//        <html>
//          <head>
//            <title>\{title}</title>
//          </head>
//          <body>
//            <p>\{text}</p>
//          </body>
//        </html>
//        """;
//        System.out.println(html);
//
//        // 记录类
//        record Rectangle(String name, double width, double height) {
//            double area() {
//                return width * height;
//            }
//        }
//        Rectangle[] zone = new Rectangle[] {
//                new Rectangle("Alfa", 17.8, 31.4),
//                new Rectangle("Bravo", 9.6, 12.4),
//                new Rectangle("Charlie", 7.1, 11.23),
//        };
//        String strTable = STR."""
//        Description  Width  Height  Area
//        \{zone[0].name}  \{zone[0].width}  \{zone[0].height}     \{zone[0].area()}
//        \{zone[1].name}  \{zone[1].width}  \{zone[1].height}     \{zone[1].area()}
//        \{zone[2].name}  \{zone[2].width}  \{zone[2].height}     \{zone[2].area()}
//        Total \{zone[0].area() + zone[1].area() + zone[2].area()}
//        """;
//
//        System.out.println(strTable);
//
//        String fmtTable = FMT."""
//        Description     Width    Height     Area
//        %-12s\{zone[0].name}  %7.2f\{zone[0].width}  %7.2f\{zone[0].height}     %7.2f\{zone[0].area()}
//        %-12s\{zone[1].name}  %7.2f\{zone[1].width}  %7.2f\{zone[1].height}     %7.2f\{zone[1].area()}
//        %-12s\{zone[2].name}  %7.2f\{zone[2].width}  %7.2f\{zone[2].height}     %7.2f\{zone[2].area()}
//        \{" ".repeat(28)} Total %7.2f\{zone[0].area() + zone[1].area() + zone[2].area()}
//        """;
//
//        System.out.println(fmtTable);
//
//
//
//        int x = 10, y = 20;
//        StringTemplate st = StringTemplate.RAW."\{x} plus \{y} equals \{x + y}";
//        List<String> fragments = st.fragments();
//        String tempRaw = String.join("\\{}", fragments);
//        // 打印模板
//        System.out.println(tempRaw);
//        List<Object> slots = st.values();
//        // 打印插值
//        System.out.println(slots);
//    }
}