package cc.momas.javase.collection.list;

import java.util.Arrays;
import java.util.List;

/**
 * List相关的示例
 *
 * @author Sod-Momas
 * @since 2021-03-02
 */
public class ListMain {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        System.out.println("create new array");
        List<String> list = Arrays.asList(
                "first",
                "second",
                "third"
        );
        list.forEach(System.out::println);

        System.out.println("=========");

        // list 转数组
        // 这里如果初始化的容量小于list的容量，将会装入一个null值
        String[] arr = new String[list.size()];
        list.toArray(arr);

        for (String s : arr) {
            System.out.println(s);
        }
    }
}
