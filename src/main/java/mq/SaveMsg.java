package mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by hjy on 16-4-15.
 */
public class SaveMsg {
    static final String BROKER_URL = "tcp://192.168.1.111:61616";
    static ActiveMQConnectionFactory factory;
    protected static final Logger log = LoggerFactory.getLogger(SaveMsg.class);


    static {
        factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
        factory.setExceptionListener(
                new ExceptionListener() {
                    public void onException(JMSException ex) {
                        log.warn("监听到ACTIVEMQ JSM 异常" + ex);
                    }
                }
        );
    }

    final ExecutorService es = Executors.newSingleThreadExecutor();
    String _DESTINATION;


    public static void main(String[] args) throws Exception {
        SaveMsg mi = new SaveMsg();
        for (int i=0; i<100; i++) {
            mi.sendRoomChat(8888, 80001, 80001, "<FONT style=\\\"FONT-FAMILY:宋体;FONT-SIZE:12px; COLOR:#000000\\\">收到来自android的测试信息" + i + "</FONT>");
            log.debug("send message,index is :" + i);
        }
        mi.conn.close();


    }

    public SaveMsg() throws Exception {
        _initConn();

    }

    public static boolean stat = true;

    private boolean isRunning() {
        return stat;
    }
    Connection conn;

    private void _initConn() throws JMSException {
        conn = factory.createConnection();
        conn.start();
    }

    /**
     *
     * @param roomId 房间ID
     * @param srcId 消息发送ID
     * @param toId 消息接收者
     * @param data　聊天消息
     */
    public void sendRoomChat(int roomId, int srcId, int toId, String data) {
        Session session;
        try {
            session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue("live.test.rsp");//amq.projId.mode.trans
            // 创建消息制作者
            MessageProducer producer = session.createProducer(destination);
            {//非持久
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            }
            MapMessage msg = session.createMapMessage();
            {//数据封装
                msg.setInt("relay_cmd_id", 17);//
                msg.setInt("vcbid", roomId);//房间ID
                msg.setInt("srcid", srcId);//当前用户ID
                msg.setInt("toid", toId);//目标用户ＩＤ
                msg.setInt("msgtype", 0);//文字聊天消息
                msg.setInt("isprivate", 0);//是否私聊（１为私聊）
                msg.setString("content", data);
            }
            log.debug("android发送给pc的消息内容为："+msg);
            producer.send(msg);
            session.close();
        } catch (JMSException ex) {
            log.warn("send android message to pc error:", ex);
        }
    }


}
