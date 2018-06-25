import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {

        System.out.println("create new array");
        List<String> list = Arrays.asList(
                "first",
                "second",
                "third"
        );
        list.forEach(System.out::println);

        System.out.println("=========");

        // list 转数组
        String [] arr = new String[list.size()];// 这里如果初始化的容量小于list的容量，将会装入一个null值
        list.toArray(arr);

        for(String s : arr){
            System.out.println(s);
        }

    }
}
