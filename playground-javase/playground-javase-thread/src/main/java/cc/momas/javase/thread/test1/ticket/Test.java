package cc.momas.javase.thread.test1.ticket;

/**
 * 用于测试网络购票在多线程下引发的数据问题
 * 
 * @author sod
 *
 */
public class Test {
	public static void main(String[] args) {
		TicketSite site = new TicketSite();
		Thread person1 = new Thread(site, "你");
		Thread person2 = new Thread(site, "黄牛");
		Thread person3 = new Thread(site, "抢票代理");
		System.out.println("开始抢票");
		person1.start();
		person2.start();
		person3.start();
	}
}
