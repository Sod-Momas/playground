package cc.momas.demo.java;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerDemo,用于测试java.util.Timer工具的用法
 * 
 * @author sothereer@gmail.com
 *
 */
public class TimerTest {

	public static void main(String[] args) {

		/**
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
		}, 2000L);

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("在指定日期执行后,每隔指定毫秒再执行一次");

			}
		}, new Date(), 4000L);

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("在推迟指定毫秒数后,每隔一定毫秒数再执行");

			}
		}, 1000L, 100);

		System.out.println("end");
		return;
	}

}
