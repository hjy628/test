package Seven.ch02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hjy on 15-8-31.
 */
public class CountingAtomic {
    public static void main(String[] args) throws InterruptedException{

        final AtomicInteger counter = new AtomicInteger();

        class CountingThread extends Thread{
            @Override
            public void run() {
                for (int x = 0; x < 10000; ++x)
                    counter.incrementAndGet();
            }
        }
        CountingThread t1 = new CountingThread();
        CountingThread t2 = new CountingThread();

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println(counter.get());

    }
}
