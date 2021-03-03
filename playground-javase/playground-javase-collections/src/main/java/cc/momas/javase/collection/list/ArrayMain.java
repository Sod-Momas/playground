package cc.momas.javase.collection.list;

/**
 * 数组相关的示例
 *
 * @author Sod-Momas
 * @since 2021-03-02
 */
public class ArrayMain {
    public static void main(String[] args) {
        arrayType();
        newArray();
    }

    private static void newArray() {
        int[] arr1 = {1, 2, 3, 4,};
        int[] arr2 = {
                111,
                222,
                333,
                444, // 末尾的逗号是可选的，这让长数组更好维护<thinking in java>
        };

        // 初始化数组的几种方式
        int[] arr3 = new int[0];
        int[] arr4 = new int[]{};
        int[] arr5 = new int[]{1, 2, 3};
        int[] arr6 = new int[]{1, 2, 3,};
    }

    private static void arrayType() {
        System.out.println(Integer.class);
        System.out.println(int.class);
        System.out.println(int[].class);
        System.out.println(int[][].class);
        System.out.println(int[][][].class);

        System.out.println(String.class);
        System.out.println(String[].class);
        System.out.println(String[][].class);

        System.out.println(Object.class);
        System.out.println(Object[].class);
    }
}
