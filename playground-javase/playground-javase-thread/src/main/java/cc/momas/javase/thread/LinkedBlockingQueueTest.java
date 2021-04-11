package cc.momas.javase.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Sod-Momas
 * @since 2021-04-11
 */
public class LinkedBlockingQueueTest {
    static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        // provider
        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the" + i + " enter");
                queue.add("user " + i);
            }
        }).start();
        // consumer
        new Thread(() -> {
            for (; ; ) {
                try {
                    System.out.println("the [" + queue.take() + "] out");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
