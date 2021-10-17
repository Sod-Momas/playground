package cc.momas.javase.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author Sod-Momas
 * @since 2021/10/17
 */
public class CountDownLatchTest {
    // 共享资源
    private static int total = 0;
    // 锁
    private static Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 模拟并发
        test1();
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
                        total++;
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
        System.out.println(total);
    }
}
