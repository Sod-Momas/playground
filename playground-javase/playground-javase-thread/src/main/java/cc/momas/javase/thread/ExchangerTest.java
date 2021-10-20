package cc.momas.javase.thread;

import java.util.concurrent.Exchanger;

/**
 * @author Sod-Momas
 * @since 2021/10/20
 */
public class ExchangerTest {
    public static void main(String[] args) {
        // 线程随机交换数据
        threadExchange();
    }

    static class ExchangeTask<T> implements Runnable {
        private final Exchanger<T> exchanger;
        private T data;

        public ExchangeTask(Exchanger<T> exchanger, T data) {
            this.exchanger = exchanger;
            this.data = data;
        }

        @Override
        public void run() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("my data = [");
            stringBuilder.append(data);
            // 交换数据
            try {
                this.data = this.exchanger.exchange(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stringBuilder.append("],after exchange my data = [");
            stringBuilder.append(data);
            stringBuilder.append("]");
            System.out.println(stringBuilder.toString());
        }

    }

    private static void threadExchange() {
        // 线程数据交换机
        Exchanger<Integer> exchanger = new Exchanger<>();

        int SIZE = 10;
        for (int i = 0; i < SIZE; i++) {
            new Thread(new ExchangeTask<Integer>(exchanger, i)).start();
        }
    }
}
