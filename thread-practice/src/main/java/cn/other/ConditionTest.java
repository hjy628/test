package cn.other;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 17-7-10.
 */
public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(new Date() + "\tThread 1 is waiting");
                try {
                    long waitTime = condition.awaitNanos(TimeUnit.SECONDS.toNanos(2));
                    System.out.println(new Date() + "\tThread 1 remaining time " + waitTime);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(new Date() + "\tThread 1 is waken up");
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try{
                System.out.println(new Date() + "\tThread 2 is running");
                try {
                    Thread.sleep(4000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                condition.signal();
                System.out.println(new Date() + "\tThread 2 ended");
            } finally {
                lock.unlock();
            }
        }).start();
    }

}
