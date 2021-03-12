package cc.momas.javase.thread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerDemo,用于测试java.util.Timer工具的用法
 *
 * @author Sod-Momas
 */
public class TimerTest {

    public static void main(String[] args) {

        /*
         * 第二个参数true时随着程序退出而退出,为false时主进程退出本进程也继续
         */
        Timer timer = new Timer("名字", false);// 守护进程

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("在指定日期执行一次");

            }
        }, new Date());

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("推迟一段时间后执行一次");

            }
        }, 1000L);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("在指定日期执行后,每隔指定毫秒再执行一次");

            }
        }, new Date(System.currentTimeMillis() + 1500), 1000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("在推迟指定毫秒数后,每隔一定毫秒数再执行");

            }
        }, 2000, 500);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException("抛异常之后整个进程会崩溃,所以timer是不可靠的");
            }
        }, 6 * 1000L);

        System.out.println("end");
    }

}
