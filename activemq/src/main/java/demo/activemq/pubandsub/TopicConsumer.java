package demo.activemq.pubandsub;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumer {
	private static final String ADDRESS = "tcp://momas.localhost:61616";
	private static final String TOPIC_NAME = "myTopic";

	public static void main(String[] args) {
		// 创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(ADDRESS);

		try {
			// 创建连接
			Connection connection = factory.createConnection();
			connection.start();
			// 创建一个会话
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建一个Destinct
			Topic destinction = session.createTopic(TOPIC_NAME);
			// 创建一个订阅者
			MessageConsumer consumer = session.createConsumer(destinction);
			// 接收消息
			consumer.setMessageListener((msg) -> System.out.println(msg));
			// 阻塞程序,避免关闭
			System.in.read();
			
			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException | IOException e) {
			e.printStackTrace();
		}

	}
}
