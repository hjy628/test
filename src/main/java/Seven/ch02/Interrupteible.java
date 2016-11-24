package Seven.ch02;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 15-8-31.
 */
public class Interrupteible {
    public static void main(String[] args) throws InterruptedException{
        final ReentrantLock l1 = new ReentrantLock();
        final ReentrantLock l2 = new ReentrantLock();

        Thread t1 = new Thread(){
            @Override
            public void run() {
             try {
                 l1.lockInterruptibly();
                 Thread.sleep(1000);
                 l2.lockInterruptibly();
             }catch (InterruptedException e){
                 System.out.println("t1 interrupted");
             }
            }
        };

/*        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    l1.lockInterruptibly();
                    Thread.sleep(1000);
                    l2.lockInterruptibly();
                }catch (InterruptedException e){
                    System.out.println("t2 interrupted");
                }
            }
        };*/
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();t1.join();
    }
}
