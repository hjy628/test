package mq;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;

import java.io.IOException;

/**
 * @auther: hjy
 * @Date: 19-12-3 11:13
 * @Description:  接收日志
 */

public class ReceiveLogs {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception{
        //初始化连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置连接的地址
        factory.setHost("localhost");

        //获得连接
        Connection connection = factory.newConnection();

        //创建channel
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        //不传递任何参数来创建一个非持久的、唯一的、动删除的队列，该队列名称由服务器随机产生
        String queueName = channel.queueDeclare().getQueue();

        //为交换器指定队列，设置binding
        channel.queueBind(queueName,EXCHANGE_NAME,"");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //消费者
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println(" [X] Received '"+message+"' ");
            }
        };

        channel.basicConsume(queueName,true,consumer);


    }




}
