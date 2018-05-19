package cc.momas.demo.thread.test1;

import cc.momas.demo.thread.test1.pojo.MyRunnable;
import cc.momas.demo.thread.test1.pojo.MyThread;

public class RunTest {

	public static void main(String[] args) {
	
		System.out.println("当前线程是:"  + Thread.currentThread().getName());
		
		MyThread t = new MyThread();
		t.start();
		System.out.println("启动的线程是:"  + t.getName());
	
		Runnable r = new MyRunnable();
		Thread t2 = new Thread(r);
		t2.start();
		System.out.println("启动的线程是:"  + t2.getName());
		
		return;
	}

}
