package cc.momas.demo.thread.pojo;

public class MyThread extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + "-" + i);
				Thread.sleep(500);
			} catch (InterruptedException ignore) {
			}
		}
	}
}
