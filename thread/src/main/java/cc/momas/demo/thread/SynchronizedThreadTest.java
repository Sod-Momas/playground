package cc.momas.demo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 更好的示例在 {@link cc.momas.demo.thread.ticket.TicketSite}
 * @see cc.momas.demo.thread.ticket.TicketSite
 * @author sod
 *
 */
public class SynchronizedThreadTest {
	public static void main(String[] args) {
		new SynchronizedThreadTest().test1();
		new SynchronizedThreadTest().test2();
		new SynchronizedThreadTest().test3();
	}

	private void test1() {
		synchronized(this) {
			System.out.println("同步代码块");
		}
	}
	
	private synchronized void test2() {
		System.out.println("同步方法");
	}
	
	Lock lock = new ReentrantLock();
	private void test3() {
		lock.lock();
		System.out.println("同步锁");
		lock.unlock();

	}
	
}
