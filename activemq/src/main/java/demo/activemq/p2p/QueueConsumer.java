package demo.activemq.p2p;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * jms消费者
 * 
 * @author sod
 *
 */
public class QueueConsumer {
	private static final String ADDRESS = "tcp://momas.localhost:61616";
	private static final String QUEUE_NAME = "myQueue";

	public static void main(String[] args) {
		syncCustomer();
		ayncCustomer();
	}

	private static void ayncCustomer() {
		try {
			// 创建连接工厂
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ADDRESS);

			// 创建连接
			Connection connection = connectionFactory.createConnection();
			// 打开连接
			connection.start();
			// 创建会话
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建一个目的地
			Queue queue = session.createQueue(QUEUE_NAME);
			// 创建一个消费者
			MessageConsumer consumer = session.createConsumer(queue);
			// consumer.setMessageListener(new MessageListener() {
			//
			// public void onMessage(Message message) {
			// if (message instanceof TextMessage) {
			// String text = "";
			//
			// try {
			// text = ((TextMessage) message).getText();
			// } catch (JMSException e) {
			// e.printStackTrace();
			// }
			// System.out.println(text);
			// }
			// }
			// });
			// 使用lambda表达式代替匿名内部类
			consumer.setMessageListener((msg) -> {
				if (msg instanceof TextMessage) {
					String text = "";
					try {
						text = ((TextMessage) msg).getText();
					} catch (JMSException e) {
						e.printStackTrace();
					}
					System.out.println(text);
				}
			});

			// 阻塞程序,使其不关闭
			System.in.read();

			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 同步消费者
	 */
	private static void syncCustomer() {
		try {
			// 创建一个工厂
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ADDRESS);

			// 创建一个连接
			Connection connection = connectionFactory.createConnection();
			// 打开连接
			connection.start();
			// 创建一个会话
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建一个目的地Destination
			Queue queue = session.createQueue(QUEUE_NAME);
			// 创建一个消费者
			MessageConsumer consumer = session.createConsumer(queue);
			while (true) {
				// 设置接收者接收消息的时间,为了便于测试,定为100s
				Message message = consumer.receive(100000);
				if (message != null) {
					System.out.println(message);
				} else {
					// 超时结束
					break;
				}
			}
			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
