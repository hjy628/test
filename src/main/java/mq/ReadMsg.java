package mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by hjy on 16-4-15.
 */
public class ReadMsg {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.111:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //第一种情况
        System.out.println("***********************");
        while (true) {
            Session session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("live.test.rsp");
            MessageConsumer consumer = session.createConsumer(destination);
            MapMessage message = (MapMessage) consumer.receive();
            //session.commit();

            System.out .println("收到消息：" +message.getString("content"));
            session.close();
        }

    }

}
