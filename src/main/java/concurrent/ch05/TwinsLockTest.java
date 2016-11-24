package concurrent.ch05;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by hjy on 16-2-25.
 */
public class TwinsLockTest {
    public void test(){
        final Lock lock = new TwinsLock();
        class Worker extends Thread{
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }

        //启动１０个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        //每隔一秒换行
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println();
            }catch (Exception e){

            }
        }

    }




}
