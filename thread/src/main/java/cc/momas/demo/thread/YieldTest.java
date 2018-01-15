package cc.momas.demo.thread;

import cc.momas.demo.thread.pojo.YeildRunableClass;

// 无法保证每次都可以礼让成功
public class YieldTest {
	public static void main(String[] args) {
		System.out.println("线程礼让");
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("test" + i);
					if(i ==6) {
						System.out.println("强势的宝贝,礼让一个");
						Thread.yield();
						System.out.println("强势的宝贝,礼让结束");
					}
				}
				
			}
		});
		Thread t2 = new Thread(new YeildRunableClass());
		t1.start();
		t2.start();
		System.out.println("测试结束");
	}
}
