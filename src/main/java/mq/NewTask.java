package mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;


/**
 * @auther: hjy
 * @Date: 19-12-3 10:14
 *   rabbitmq测试
 * @Description: 发送消息的任务发送程序
 */

//@Slf4j
public class NewTask {

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

        //从控制台参数中，获取消息
        String message = getMessage(args);

        //发送消息，发送10条
        for (int i = 0; i < 10; i++) {
            String msg = message + " " + i;
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,(msg).getBytes("UTF-8"));
            System.out.println(" [x] Sent '"+ msg+"'");
        }
        channel.close();

        connection.close();
    }


    private static String getMessage(String[] strings){
        if (strings.length <1){
            return "Hello World!";
        }
        return joinStrings(strings,"    ");
    }


    private static String joinStrings(String[] strings,String delimiter){
        int length = strings.length;
        if (length == 0)
            return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }


}
