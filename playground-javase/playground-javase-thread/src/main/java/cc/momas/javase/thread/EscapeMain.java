package cc.momas.javase.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Sod-Momas
 * @since 2021-03-20
 */
public class EscapeMain {
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static boolean 下班时间 = false;

    public static void main(String[] args) throws InterruptedException {
        // 启动打印机
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();

        // 奴隶上班
        new Thread(() -> {
            //奴隶要不断工作，除非下班
            while (!下班时间) {
                // 不能下班的干活 phase 1
                try {
                    queue.put("不能下班的干活");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 不能下班的干活 phase 1

                // 能下班的干活 phase 2
//                 System.out.println("能下班的干活");
                // 能下班的干活 phase 2
            }
        }).start();

        // 老板上班,奴隶主上班就是睡觉啦
        TimeUnit.SECONDS.sleep(8);
        下班时间 = true; // 给奴隶放假
        queue.put("下班啦");
    }
}
