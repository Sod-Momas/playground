package cc.momas.demo.thread.test1;

import cc.momas.demo.thread.test1.pojo.MyRunnable;

public class StateTest {

	public static void main(String[] args) {
		Thread t = new Thread(new MyRunnable());
		System.out.println("新建线程");
		t.start();
		System.out.println("线程已开始");
	}
}
