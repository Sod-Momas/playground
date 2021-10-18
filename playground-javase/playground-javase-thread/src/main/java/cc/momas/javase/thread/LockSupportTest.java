package cc.momas.javase.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Sod-Momas
 * @since 2021/10/18
 */
public class LockSupportTest {
    public static void main(String[] args) {
        // LockSupport的基本使用
        basic();
    }

    private static void basic() {
        int count = 10;
        Thread t0 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                System.out.println("-线程准备摸鱼");
                LockSupport.park();
                System.out.println("-线程工作");
            }
        });

        t0.start();

        try {
            for (int i = 0; i < count; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("+唤醒线程");
                LockSupport.unpark(t0);
                System.out.println("+唤醒完毕");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
