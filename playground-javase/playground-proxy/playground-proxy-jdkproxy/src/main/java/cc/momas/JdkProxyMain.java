package cc.momas;

import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * @author Sod-Momas
 * @since 2020/11/2
 */
public class JdkProxyMain {
    public static void main(String[] args) {
        // jdk9之后保存代理对象的字节码文件
        final String prop = "jdk.proxy.ProxyGenerator.saveGeneratedFiles";
        // 生成的对象使用java5格式（49版本）来保存
        final String prop49 = "jdk.proxy.ProxyGenerator.v49";

        System.setProperty(prop, Boolean.TRUE.toString());
        System.setProperty(prop49, Boolean.TRUE.toString());

        final Car car = new CarImpl(); //目标产生
        final int originValue = car.returnValue();
        System.out.println("原始对象返回值：" + originValue);

        car.fix();
        car.sellCar();
        car.show("原本的对象");

        // 对 car对象进行代理
        Car carProxy = (Car) Proxy.newProxyInstance(
                car.getClass().getClassLoader(),
                car.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    if (method.getName().equals("fix")) {
                        System.out.println("加收500元");
                        Object o = method.invoke(car);//调用目标的方法
                        System.out.println("送货上门");
                        return o;
                    }
                    if (method.getName().equals("sellCar")) {
                        System.out.println("加收5000元");
                        Object o = method.invoke(car);
                        System.out.println("送货上门");
                        return o;
                    }
                    if (method.getName().equals("show")) {
                        args1[0] = "德国" + args1[0];
                        return method.invoke(car, args1);
                    }
                    if (method.getName().equals("reutnValue")) {
                        Integer invoke = (Integer) method.invoke(car);
                        return invoke - new Random().nextInt();
                    }
                    return method.invoke(car, args1);
                });
        int proxyReturnValue = carProxy.returnValue();
        System.out.println("代理对象返回值：" + proxyReturnValue);

        carProxy.fix();
        carProxy.sellCar();
        carProxy.show("代理的对象");
    }
}
