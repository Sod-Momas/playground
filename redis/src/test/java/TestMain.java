import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestMain {


	String redisHost  = "10.10.23.128";
	Integer redisPort = 6379;

	@Test
	public void test(){
		Jedis j = new Jedis(this.redisHost, this.redisPort);
		//设置键值
		j.set("test1", "value1");
		//取出键对应的值
		String value = j.get("test1");
		//打印
		System.out.println(value);
	}
}
