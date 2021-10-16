package cc.momas.autoconfig;

/**
 * @author Sod-Momas
 * @since 2021/10/15
 */
public class MyStarter {
    public MyStarter() {
        System.out.println("instantiate " + getClass() + " by classLoader :" + getClass().getClassLoader());
        new RuntimeException().printStackTrace();
    }
}
