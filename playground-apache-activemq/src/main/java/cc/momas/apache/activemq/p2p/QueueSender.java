package cc.momas.apache.activemq.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 * jms生产者
 *
 * @author Sod-Momas
 */
public class QueueSender {

    private static final String ADDRESS = "tcp://momas.localhost:61616";
    private static final String QUEUE_NAME = "myQueue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ADDRESS);

        try {
            // 从工厂对象中获得连接
            Connection connection = connectionFactory.createConnection();
            // 开启连接
            connection.start();

            // ----

            //开启一个会话,第一个参数指定不使用事务,第二个参数指定客户端接收消息的确认方式
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个目的地Queue或者是Topic
            Queue queue = session.createQueue(QUEUE_NAME);
            //创建一个生产者
            MessageProducer producer = session.createProducer(queue);
            //创建message
            ActiveMQTextMessage message = new ActiveMQTextMessage();
            message.setText("hello");

            boolean b = true;
            while (b) {
                Thread.sleep(2000);
                //发送消息
                producer.send(message);
            }


            //关闭
            producer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
