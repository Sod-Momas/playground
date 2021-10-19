package cc.momas.javase.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sod-Momas
 * @since 2021/10/19
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        // 等待所有线程完成后再执行某个操作
        momWaitAllBaby();
    }

    private static void momWaitAllBaby() {
        // 宝宝们有各自相同的任务
        class Baby implements Runnable {
            private final CountDownLatch countDownLatch;
            private final String babyName;

            public Baby(CountDownLatch countDownLatch, String babyName) {
                this.countDownLatch = countDownLatch;
                this.babyName = babyName;
            }

            @Override
            public void run() {
                try {
                    // 模拟执行任务阻塞,随机时间
                    Thread.sleep(new Random().nextInt(1000));
//                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(babyName + " is doing work...done");
                countDownLatch.countDown();
            }
        }
        // 同时执行任务的数量
        int SIZE = 10;
        // 计数器,妈妈
        final CountDownLatch mom = new CountDownLatch(SIZE);
        // 用来执行任务的线程池，也可以直接 new Thread().start();
        final ExecutorService executor = Executors.newFixedThreadPool(SIZE);

        for (int i = 0; i < SIZE; i++) {
            // 执行任务
            executor.submit(new Baby(mom, "baby" + i));
        }

        try {
            System.out.println("mom is waiting...");
            // 妈妈等待所有宝宝执行完任务
            mom.await();
            System.out.println("all task done");
            // 关闭线程池，让程序正常退出
            executor.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
