package cc.momas.javase.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Sod-Momas
 * @since 2021/10/20
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        // 所有选手同时起跑
        runAtSameTime();
    }

    private static void runAtSameTime() {
        /*
         * 起跑选手
         */
        class Runner implements Runnable {
            private final CyclicBarrier cyclicBarrier;
            private final String runnerName;

            Runner(CyclicBarrier cyclicBarrier, String runnerName) {
                this.cyclicBarrier = cyclicBarrier;
                this.runnerName = runnerName;
            }

            @Override
            public void run() {
                try {
                    // 选手已经准备好了
                    System.out.println(runnerName + " already to run");
                    // 进入等待
                    cyclicBarrier.await();
                    System.out.println(runnerName + " run at " + System.currentTimeMillis());
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
        // 选手数量
        int SIZE = 10;
        // 裁判
        final CyclicBarrier judgment = new CyclicBarrier(SIZE);

        for (int i = 0; i < SIZE; i++) {
            // 所有选手都准备好之后自动全部开跑
            new Thread(new Runner(judgment, "Runner" + i)).start();
        }

        try {
            // 第一回合结束
            Thread.sleep(1000);
            System.out.println("中场休息=========================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 与CountDownLatch 最大的不同在于 CyclicBarrier 可以重复使用
        for (int i = 0; i < SIZE; i++) {
            // 所有选手都准备好之后自动全部开跑
            new Thread(new Runner(judgment, "Runner" + i)).start();
        }
    }
}
