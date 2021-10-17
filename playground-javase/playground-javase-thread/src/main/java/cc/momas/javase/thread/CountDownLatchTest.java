package cc.momas.javase.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author Sod-Momas
 * @since 2021/10/17
 */
public class CountDownLatchTest {
    // 共享资源
    private static int test1_res = 0;
    private static int test2_res = 0;

    // 锁
    private final static Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 不安全的并发
        test1();
        // 安全的并发(synchronized)
        test2();
    }

    // 模拟并发
    private static void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 等待其他资源
                    countDownLatch.await();
                    for (int j = 0; j < 1000; j++) {
                        // 资源使用（写入）
                        test1_res++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
        Thread.sleep(1000);
        countDownLatch.countDown();
        Thread.sleep(1000);
        // 输出资源
        // 会输出一个小于 10,000 的数字
        System.out.println("unsafe: " + test1_res);
    }

    // 安全的并发(synchronized)
    private static void test2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 等待其他资源
                    countDownLatch.await();
                    for (int j = 0; j < 1000; j++) {
                        // 加锁，保护total这个资源的同步安全性
                        synchronized (LOCK) {
                            // 资源使用（写入）
                            test2_res++;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
        Thread.sleep(1000);
        countDownLatch.countDown();
        Thread.sleep(1000);
        // 输出资源
        // 输出 10,000，因为使用 synchronized 关键字保护了同步资源
        System.out.println("synchronized: " + test2_res);
    }
}
