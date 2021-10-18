package cc.momas.javase.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Sod-Momas
 * @since 2021/10/18
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        // 基本用法,获取不到锁时会一直等待
        basic();
        // 流量降级，获取不到锁时执行另一个操作
        fallbackTest();
    }

    private static void fallbackTest() {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
//            try {
            new FallbackTask("fallback task" + i, semaphore).start();
//                TimeUnit.MILLISECONDS.sleep(600);
//                new FallbackTask("fallback task" + i, semaphore).start();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }

    /**
     * 获取不到锁时会流量降级的任务
     */
    static class FallbackTask extends Thread {
        private final Semaphore semaphore;

        public FallbackTask(String name, Semaphore semaphore) {
            super(name);
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 尝试获取公共资源，超时1秒
                if (semaphore.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println(getName() + " thread acquire at time:" + System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(4);
                    // 释放公共资源
                    semaphore.release();
                } else {
                    // 获取不到锁，流量降级
                    fallback();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void fallback() {
            System.out.println("降级");
        }
    }

    // 基本用法：用于控制同时执行的线程数量
    private static void basic() {
        // 允许同时运行两个线程
        Semaphore semaphore = new Semaphore(2);

        // 开10个线程
        for (int i = 0; i < 10; i++) {
            new TaskBaseSemaphore(semaphore, "task" + i).start();
        }
    }

    static class TaskBaseSemaphore extends Thread {
        private final Semaphore semaphore;

        TaskBaseSemaphore(Semaphore semaphore, String name) {
            super(name);
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获取公共资源
                semaphore.acquire();
                System.out.println(getName() + " thread acquire at time:" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(2);
                // 释放公共资源
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
