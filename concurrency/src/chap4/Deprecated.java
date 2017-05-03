package chap4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-5-3.
 */
public class Deprecated {

    public static void main(String[] args) throws Exception{
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(),"printTHread");
        printThread.setDaemon(true);
        printThread.start();
        TimeUnit.SECONDS.sleep(3);
        //将printThread进行暂停，输出内容工作停止
        printThread.suspend();
        System.out.println("main suspend PrintThread at "+ format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        //将printThread进行恢复，输出内容工作继续
        printThread.resume();
        System.out.println("main resume PrintThread at "+ format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        //将printThread进行终止，输出内容工作停止
        printThread.stop();
        System.out.println("main stop PrintThread at "+ format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }



    static class Runner implements Runnable{

        @Override
        public void run() {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            while (true){
                System.out.println(Thread.currentThread().getName()+" Run at "+dateFormat.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
