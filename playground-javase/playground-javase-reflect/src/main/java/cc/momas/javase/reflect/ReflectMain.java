package cc.momas.javase.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
/**
 * @author Sod-Momas
 * @since 2021-02-27
 */
public class ReflectMain {

    public static void main(String[] args) {

        ReflectMain main = new ReflectMain();
        try {
            main.test1();
            main.test2();
            main.test3();
            main.test4();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void test4() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        String className = "cc.momas.javase.reflect.User";
        Class<?> clazz = Class.forName(className);

        Field[] fields = clazz.getDeclaredFields();// 取出所有字段
        // 打印所有字段
        for (Field f : fields) {
            System.out.println("字段类型 : " + f.getType() + "\t字段名 : " + f.getName());
        }

        Object obj = clazz.newInstance(); // 实例化对象
        Field objName = clazz.getDeclaredField("name");// 找到name字段
        objName.setAccessible(true); // 解除封装(private)
        objName.set(obj, "通过字段反射设置"); // 设置值,相当于 obj.name = "xxx";

        System.out.println(obj);

    }


    public void test3() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String name = "反射设置名字";
        String className = "cc.momas.javase.reflect.User";
        Class<?> clazz = Class.forName(className);

        Object obj = clazz.newInstance();

        // 获取getter方法
        Method getName = clazz.getMethod("getName");
        // 获取settter方法
        Method setName = clazz.getMethod("setName", String.class);

        setName.invoke(obj, name);// 执行setter,相当于obj.setName(name);
        Object attr = getName.invoke(obj); // 执行getter,相当于obj.getName();

        System.out.println("设置并取出的属性 : " + attr);
        System.out.println("对象toString : " + obj.toString());
    }

    /**
     * 使用构造器实例化
     */
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        String className = "cc.momas.javase.reflect.User";
        Class<?> clazz = Class.forName(className);

        // 获取无参构造器
        Constructor<?> constractor = clazz.getConstructor();
        Object obj = constractor.newInstance(); // 使用构造器实例化对象
        System.out.println(obj);

        // 获取其他构造器
        Constructor<?> c = clazz.getConstructor(Integer.TYPE, String.class, Date.class);
        obj = c.newInstance(100, "全参构造对象", new Date());
        System.out.println(obj);
    }


    /**
     * 简单实例化对象并打印
     */
    public void test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String className = "cc.momas.javase.reflect.User";

        Class<?> clazz = Class.forName(className);
//        Object obj = clazz.newInstance();
//        System.out.println(obj);
        final Object user = clazz.getDeclaredConstructor().newInstance();
        System.out.println(user);

    }

    /**
     * 工厂方法,根据全类名实例化一个对象出来
     *
     * @param className 全类名
     * @return 该类的对象, 强转一下就好了
     */
    public Object getInstance(String className) {
        Class<?> clazz = null;
        Object obj = null;

        try {
            clazz = Class.forName(className);
            obj = clazz.newInstance();

        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
