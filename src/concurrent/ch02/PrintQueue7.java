package concurrent.ch02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 15-8-17.
 */
public class PrintQueue7 {
    private final Lock queueLock = new ReentrantLock(false);

    public void printJob(Object document){
        queueLock.lock();
        try {
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+":PrintQueue : Printing a Job during " + (duration/1000) + " seconds");
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
        queueLock.lock();
        try {
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+":PrintQueue : Printing a Job during " + (duration/1000) + " seconds");
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }

    }
}
