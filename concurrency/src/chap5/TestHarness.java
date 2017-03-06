package chap5;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-3-6.
 * 计时测试中使用CountDownLatch来启动和停止线程
 * Exception in thread "main" java.lang.IllegalMonitorStateException
 at java.lang.Object.wait(Native Method)
 at java.lang.Object.wait(Object.java:502)  ???梳理不清楚
 */
public class TestHarness {

    public static void main(String[] args) throws Exception{
        TestHarness demo = new TestHarness();

      final   Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("工作线程："+Thread.currentThread().getName());
            }
        };

       long allTime =  demo.timeTasks(10, runnable);


        System.out.println(allTime);

    }

    public  long timeTasks(int nThreads,final Runnable task)throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException ignored){

                    }
                }
            };
            t.start();
        }


        long start = System.nanoTime();
        startGate.countDown();
        TimeUnit.SECONDS.sleep(2);

            endGate.wait();

        long end = System.nanoTime();
        return end-start;


    }
}
