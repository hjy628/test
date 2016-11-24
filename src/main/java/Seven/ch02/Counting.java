package Seven.ch02;

/**
 * Created by hjy on 15-8-18.
 */
public class Counting {
    public static void main(String[] args) throws InterruptedException{
        class Counter{
            private int count=0;
            public synchronized void increment(){++count;}
            public int getCount(){return count;}
        }
        final Counter counter=new Counter();
        class CountingThread extends Thread{
            @Override
            public void run() {
                for (int i = 0; i < 10000; ++i) {
                    counter.increment();
                }
            }
        }
        CountingThread t1=new CountingThread();
        CountingThread t2=new CountingThread();
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println(counter.getCount());
    }
}
