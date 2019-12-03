package mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * @auther: hjy
 * @Date: 19-12-2 16:11
 * @Description:
 */

public class Consumer implements MessageListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static final String SUBJECT = "money";

    public static void main(String[] args) throws JMSException {

        //初始化ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

        //创建MQ连接
        Connection connection = connectionFactory.createConnection();

        //启动连接
        connection.start();

        //创建会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //通过会话创建目标
        Destination dest = session.createQueue(SUBJECT);

        //创建MQ消息的消费者
        MessageConsumer consumer = session.createConsumer(dest);

        //初始化MessageListener
        Consumer me = new Consumer();

        //给消费者设定监听对象
        consumer.setMessageListener(me);
    }

    @Override
    public void onMessage(Message message) {
            TextMessage textMessage = (TextMessage)message;
            try {
                LOGGER.info("get message "+textMessage.getText());
            }catch (JMSException e){
                LOGGER.error("error {}",e);
            }
    }
}
