package concurrent.ch03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 15-8-17.
 */
public class PrintQueue3 {
    private final Semaphore semaphore;

    private boolean freePrinters[];

    private Lock lockPrienters;

    public PrintQueue3() {
        semaphore=new Semaphore(3);
        freePrinters=new boolean[3];
        for (int i = 0; i < 3; i++) {
            freePrinters[i]=true;
        }
        lockPrienters=new ReentrantLock();
    }

    public void printJob(Object document){
        try {
            semaphore.acquire();
            int assignedPrinter = getPrinter();
            long duration = (long)(Math.random()*10);
            System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n",Thread.currentThread().getName(),assignedPrinter,duration);
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    private int getPrinter(){
        int ret = -1;
        try {
            lockPrienters.lock();
            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]){
                    ret=i;
                    freePrinters[i]=false;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lockPrienters.unlock();
        }
        return ret;
    }
}
