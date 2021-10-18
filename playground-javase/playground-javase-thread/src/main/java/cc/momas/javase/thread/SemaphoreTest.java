package cc.momas.javase.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Sod-Momas
 * @since 2021/10/18
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        // 基本用法
        basic();
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
