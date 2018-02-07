package cc.momas.demo.thread;

public class SleepTest {

	public static void main(String[] args) {
		System.out.println("睡眠测试");
		for (int i = 0; i < 5 ;) {
			System.out.println(++i + "秒");
			try {
				Thread.sleep(1000);//睡眠一秒
			} catch (InterruptedException e) {
				System.out.println("线程中断");
			}
		}
		System.out.println("睡眠结束");
	}
}
