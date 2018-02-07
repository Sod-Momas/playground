package cc.momas.demo.thread;

import cc.momas.demo.thread.pojo.MyRunnable;

public class JoinTest {
	public static void main(String[] args) {
		System.out.println("线程join测试");
		//创建线程
		Thread tmp = new Thread(new MyRunnable());
		tmp.start();
		
		for (int i = 0; i < 3; i++) {
			System.out.println("进入循环:"+i);
			if(i == 1) {
				try {
					tmp.join();//阻塞主线程,使子线程强制执行
				} catch (InterruptedException e) {
					System.out.println("子线程被阻断");
				}
			}
		}
		System.out.println("主线程继续执行");
	}
}
