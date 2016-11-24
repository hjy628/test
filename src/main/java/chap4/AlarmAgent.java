package chap4;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * Created by hjy on 16-1-10.
 * 负责连接告警服务器，并发送告警信息至告警服务器
 */
public class AlarmAgent {
    //用于记录AlarmAgent是否连接上告警服务器
    private volatile boolean connectedToServer = false;

    //模式角色：GuardedSuspension.Predicate
    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    //模式角色：guardedSuspension.Blocker
    private final Blocker blocker = new ConditionVarBlocker();

    //心跳定时器
    private final Timer heartbeatTimer = new Timer(true);

    //省略其他代码

    /**
    * 发送告警信息
     * @param alarm 告警信息
    * @throws java.lang.Exception
    */
    public void sendAlarm(final String alarmInfo)throws Exception{
        //可能需要等待，直到AlarmAgent连接上告警服务器（或者连接中断后重新连上服务器）
        //模式角色：GuardedSuspension.GuardedAction
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarmInfo);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    //通过网络连接将告警信息发送给告警服务器
    private void doSendAlarm(String alarmInfo){
        //省略其他代码
        System.out.println("sending alarm "+alarmInfo);
        //模拟发送告警至服务器的耗时
        try {
            Thread.sleep(50);
        }catch (Exception e){

        }
    }

    public void init(){
        //省略其他代码

        //告警连接线程
        Thread connectingThread = new Thread(new ConnectingTask());
        connectingThread.start();

        heartbeatTimer.schedule(new HeartbeatTask(),60000,2000);
    }

    public void disconnect(){
        //省略其他代码
        System.out.println("disconnected from alarm server.");
        connectedToServer = false;
    }

    protected void onConnected(){
        try {
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    connectedToServer = true;
                    System.out.println("connected to server");
                    return Boolean.TRUE;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void onDisconnected(){
        connectedToServer = false;
    }


    //负责与告警服务器建立网络连接
    private class ConnectingTask implements Runnable{
        @Override
        public void run() {
            //省略其他代码

            //模拟连接操作耗时
            try {
                Thread.sleep(100);
            }catch (Exception e){

            };
        }
    }

    /**
    * 心跳定时任务：定时检查与告警服务器的连接是否正常，发现链接异常后自动重新连接
    */
    private class HeartbeatTask extends TimerTask{
        //省略其他代码

        @Override
        public void run() {
            //省略其他代码
            if (!testConnection()){
                onDisconnected();
                reconnect();
            }

        }
    }

    private boolean testConnection(){
        //省略其他代码
        return true;
    }

    private void reconnect(){
        ConnectingTask connectingThread = new ConnectingTask();

        //直接在心跳定时器线程中执行
        connectingThread.run();
    }
}
