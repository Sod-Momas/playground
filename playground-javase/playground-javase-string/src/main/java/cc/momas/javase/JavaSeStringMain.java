package cc.momas.javase;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class JavaSeStringMain {
    public static void main(String[] args) {
//        test1();
//        test2();

        final Unsafe unsafe = getUnsafe();
        assert unsafe != null;

        System.out.println(unsafe.addressSize());
    }

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    private static void test2() {
        String a = "1";
        String b = "2";
    }

    private static void test1() {
        String a = "11";
        String b = new String("11");

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
