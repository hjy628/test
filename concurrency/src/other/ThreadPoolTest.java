package other;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hjy on 17-2-14.
 */
public class ThreadPoolTest {

   // final BlockingQueue<Runnable> queue = new SynchronousQueue<Runnable>();
    final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);

    final ThreadPoolExecutor executor = new ThreadPoolExecutor(10,600,30, TimeUnit.SECONDS,queue,
            Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

    final AtomicInteger completedTask = new AtomicInteger(0);

    final AtomicInteger rejectedTask = new AtomicInteger(0);

    static long beginTime;

    final int count = 1000;

    public static void main(String[] args) {
        beginTime = System.currentTimeMillis();
        ThreadPoolTest test = new ThreadPoolTest();
        test.start();
    }


    public void  start(){
        CountDownLatch latch = new CountDownLatch(count);
        CyclicBarrier barrier = new CyclicBarrier(count);
        for (int i = 0; i < count; i++) {
            new Thread(new TestThread(latch,barrier)).start();
        }
        try {
            latch.await();
            executor.shutdownNow();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    class TestThread implements Runnable{

        private CountDownLatch latch;

        private CyclicBarrier barrier;

        public TestThread(CountDownLatch latch, CyclicBarrier barrier) {
            this.latch = latch;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            try {
                executor.execute(new Task(latch));
            }
            catch (RejectedExecutionException ex){
                latch.countDown();

                System.out.println("被拒绝的任务数为： 用地 " + rejectedTask.incrementAndGet());
            }
        }
    }

    class Task implements Runnable{

        private CountDownLatch latch;

        public Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("执行的任务数为： "+ completedTask.incrementAndGet());

            System.out.println("任务耗时为： " + (System.currentTimeMillis()-beginTime) + "ms");

            latch.countDown();

        }
    }


}
