package fiedemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hjy on 17-2-21.
 */
public class LogControl {
    private static final long INTERVAL=1000;
    private static final long PUNISH_TIME=5000;
    private static final long ERROR_THRESHOLD=100;
    private static AtomicInteger count;
    private static long beginTime;
    private static long punishTimeEnd;

    //由于控制不用非常精确，因此忽略此处的并发问题

    public static boolean isLog(){
        //如果尚处于不写日志阶段，则返回false
        if(punishTimeEnd>0  && punishTimeEnd < System.currentTimeMillis()){
            return false;
        }

        //如果count为0，则说明是重新计数，并设置beginTime
        if (count.getAndIncrement()==0){
            beginTime=System.currentTimeMillis();
            return true;
        } //如已在计数
        else{

            //判断累积数是否超过了阈值，超过了则将count设置为0，并设置一定的不写日志的时间
            if (count.intValue() > ERROR_THRESHOLD){
                count.set(0);
                punishTimeEnd = PUNISH_TIME + System.currentTimeMillis();
                return false;
            }
            //如果没有超过阈值，且当前时间已超过计数周期，则重新计算
            else if (System.currentTimeMillis() > (beginTime+INTERVAL)){
                count.set(0);
            }
            return true;
        }
    }

    public static void main(String[] args) {
        if (LogControl.isLog()){
            //log.error(errorInfo,throwable);
        }
    }
}


