package other;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by hjy on 17-2-14.
 */
public class ReentrantReadWriteLockExample {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

   // private static ReentrantLock lock = new ReentrantLock();

    private static Map<String,String> maps = new HashMap<String,String>();
    private static CountDownLatch latch = new CountDownLatch(102);
    private static CyclicBarrier barrier = new CyclicBarrier(102);


    public static void main(String[] args) throws Exception{

        TimeUnit.SECONDS.sleep(12);

        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(new ReadThread()).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new WriteThread()).start();
        }
        latch.await();

        long endTime = System.currentTimeMillis();
        System.out.println("Consume Time is: " + (endTime-beginTime)+" ms");


    }

    static class WriteThread implements Runnable{
        @Override
        public void run() {
            try {
                barrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                //lock.lock();
                writeLock.lock();
                maps.put("1,","2");
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
               // lock.unlock();
                writeLock.unlock();
            }
            latch.countDown();
        }
    }

    static class ReadThread implements Runnable{
        @Override
        public void run() {
            try {
                barrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
              //  lock.lock();
                lock.readLock();
                maps.get("1");
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
              //  lock.unlock();
                lock.readLock();
            }
            latch.countDown();
        }
    }


}
