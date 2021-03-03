package cc.momas.apache.activemq.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;


/**
 * @author Sod-Momas
 */
public class TopicProducer {
    private static final String ADDRESS = "tcp://localhost:61616";
    private static final String TOPIC_NAME = "myTopic";

    public static void main(String[] args) {
        try {
            //创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ADDRESS);

            //创建连接
            Connection connection = connectionFactory.createConnection();
            //开启连接
            connection.start();
            //创建一个会话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个Destination,queue或者Topic
            Topic topic = session.createTopic(TOPIC_NAME);
            //创建一个发布者
            MessageProducer producer = session.createProducer(topic);
            //创建一个消息
            TextMessage textMessage = new ActiveMQTextMessage();
            textMessage.setText("hello my topic");
            boolean b = true;
            while (b) {
                //发送消息
                producer.send(textMessage);
                Thread.sleep(1000);
            }

            producer.close();
            session.close();
            connection.close();
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
