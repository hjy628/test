package mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @auther: hjy
 * @Date: 19-12-3 11:09
 * @Description:  发送日志
 */

public class EmitLog {

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

        //声明交换器和类型
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        String message = getMessage(args);

        //往交换器上发送消息
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '"+ message + "' ");

        channel.close();
        connection.close();

    }

    private static String getMessage(String[] strings){
        if (strings.length <1){
            return "info:Hello World!";
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
