package cc.momas.java.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * 一个简单的Redis客户端demo
 * @author Administrator
 *
 */
public class MainTest {

	String redisHost  = "10.10.23.128";
	Integer redisPort = 6379;

	public static void main(String[] args) {
		new MainTest().test1();
	}

	public void test1() {
		
		Jedis j = new Jedis(this.redisHost, this.redisPort);
		//设置键值
		j.set("test1", "value1");
		//取出键对应的值
		String value = j.get("test1");
		//打印
		System.out.println(value);
		
	}
}
