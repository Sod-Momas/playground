import org.junit.Test;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static int count = 0;

	/**
	 * 动态改变循环次数
	 */
	@Test
	public void print() {
		new Timer("数量统计", false)
				.schedule(new TimerTask() {
					          @Override
					          public void run() {
						          TimerTest.count++;
						          System.out.println("当前数据量为:" + TimerTest.count);
					          }
				          },
						0, 300);

		try {
			Thread.currentThread().sleep(5000);
			for (int i = 0; i < count; i++) {
				System.out.println("下标 : " + i);
				System.out.println("总量 : " + count);
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("退出");
	}
}
