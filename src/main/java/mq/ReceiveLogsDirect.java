package mq;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @auther: hjy
 * @Date: 19-12-3 11:13
 * @Description:  接收日志
 */

public class ReceiveLogsDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception{
        //初始化连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置连接的地址
        factory.setHost("localhost");

        //获得连接
        Connection connection = factory.newConnection();

        //创建channel
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        //不传递任何参数来创建一个非持久的、唯一的、动删除的队列，该队列名称由服务器随机产生
        String queueName = channel.queueDeclare().getQueue();

        if (args.length < 1){
            System.err.println("Usage:ReceiveLogsDirect[info][warning][error]");
            System.exit(1);
        }

        //为一个我们感兴趣的severity创建一个新的绑定
        for (String severity: args){
            channel.queueBind(queueName,EXCHANGE_NAME,severity);
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //消费者
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println(" [X] Received '"+envelope.getRoutingKey()+"':'"+message+"' ");
            }
        };

        channel.basicConsume(queueName,true,consumer);


    }




}
