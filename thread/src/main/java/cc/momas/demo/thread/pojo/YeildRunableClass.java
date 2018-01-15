package cc.momas.demo.thread.pojo;

public class YeildRunableClass implements Runnable{

	@Override
	public void run() {
		System.out.println("会礼让的线程开始执行");
		for (int i = 0; i < 5 ; i++) {
			System.out.println("运行 " + i + "次");
			if(i == 3) {
				System.out.println("线程礼让");
				Thread.yield();
				System.out.println("礼让完成,继续执行");
			}
		}
		
	}

	
}
