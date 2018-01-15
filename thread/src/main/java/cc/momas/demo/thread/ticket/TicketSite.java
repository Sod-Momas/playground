package cc.momas.demo.thread.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
public class TicketSite implements Runnable{

	/**
	 * 获取一个锁,用来锁住线程访问
	 */
	Lock lock = new ReentrantLock();
	/**
	 * 剩余票数
	 */
	private int count = 10;
	/**
	 * 记录买到的是第几张票
	 */
	private int num = 0;
	
	@Override
	public void run() {
		
		// this.unsafe();
//		this.sychronizedBlock();
//		 this.sychronized();
//		 this.lockBlock();
	}
	/**
	 * 使用锁的方式保证数据的正常访问
	 */
	private void lockBlock() {
		while(true) {
			//没有票的时候跳出循环
			if(count < 0) {
				break;
			}
			try {
				//锁住数据
				lock.lock();
				//第一步,修改数据
				num++;
				count--;
				
				//模拟网络延时
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				//第二步,显示购买信息
				System.out.println(Thread.currentThread().getName() + "抢到第" + num +"张票");
			} catch (Exception e) {
			} finally {
				//一定要在finnaly里解锁,否则当异常发生的时候,会发生死锁现象
				lock.unlock();
			}
		}
		
		
	}
	/**
	 * 使用了同步方法,保证数据安全
	 */
	private synchronized void sychronized() {
		while(true) {
			synchronized (this) {
				//没有票的时候跳出循环
				if(count < 0) {
					break;
				}
				//第一步,修改数据
				num++;
				count--;
				
				//模拟网络延时
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				//第二步,显示购买信息
				System.out.println(Thread.currentThread().getName() + "抢到第" + num +"张票");	
			}
		}
		
	}
	/**
	 * 使用了同步方法块,保证了数据安全
	 */
	private void sychronizedBlock() {
		while(true) {
			synchronized (this) {
				//没有票的时候跳出循环
				if(count < 0) {
					break;
				}
				//第一步,修改数据
				num++;
				count--;
				
				//模拟网络延时
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				//第二步,显示购买信息
				System.out.println(Thread.currentThread().getName() + "抢到第" + num +"张票");	
			}
		}
		
	}
	/**
	 * 不安全的方法,有数据安全问题
	 */
	private void unsafe() {
		while(true) {
			//没有票的时候跳出循环
			if(count < 0) {
				break;
			}
			//第一步,修改数据
			num++;
			count--;
			
			//模拟网络延时
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			//第二步,显示购买信息
			System.out.println(Thread.currentThread().getName() + "抢到第" + num +"张票");
		}
		
	}

}
