package mq;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @auther: hjy
 * @Date: 19-12-3 10:34
 * rabbitmq示例
 * @Description:  接收消息的工作者
 */

//@Slf4j
public class Worker {

    private static final String TASK_QUEUE_NAME = "hjy_queue";

    public static void main(String[] args) throws Exception{

            //初始化连接工厂
            ConnectionFactory factory = new ConnectionFactory();

            //设置连接的地址
            factory.setHost("localhost");

            //获得连接
            Connection connection = factory.newConnection();

            //创建channel
            Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);

        channel.basicQos(1);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //消费者
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println(" [X] Received '"+message+"' ");
                try {
                        doWork(message);
                }finally {
                    System.out.println(" [x] Done");
                    //确认消息
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        //取消autoack
        channel.basicConsume(TASK_QUEUE_NAME,false,consumer);
    }


    private static void doWork(String task){
        for (char ch:task.toCharArray()) {
            if (ch == '.'){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException _ignored){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


}
