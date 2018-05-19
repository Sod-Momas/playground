package cc.momas.demo.thread.test1.pojo;

public class MyRunnable implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("Runnable正在运行");
				System.out.println(Thread.currentThread().getName() + "-" + i);
				Thread.sleep(500);
				System.out.println("Runnable在阻塞后继续运行");
				
			} catch (InterruptedException ignore) {
				System.out.println("Runnable被中断");
			}
		}
	}

}
