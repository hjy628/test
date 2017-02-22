package threadandlock;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by hjy on 17-2-22.
 * 简单使用一个多线程来对比无阻塞Stack和传统Stack的性能，测试方法为创建300个线程,每个线程均执行10次push和pop动作
 *
 * Stack consume Time: 90 ms    ConcurrentStack consume Time: 26 ms
 * Stack consume Time: 113 ms   ConcurrentStack consume Time: 29 ms
 * Stack consume Time: 60 ms    ConcurrentStack consume Time: 36 ms
 * Stack consume Time: 60 ms    ConcurrentStack consume Time: 24 ms
 */
public class StackBenchmark {

    private Stack<String> stack = new Stack<String>();

    private ConcurrentStack<String> concurrentStack = new ConcurrentStack<String>();

    private static final int THREAD_COUNT = 300;

    private CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

    private CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT);

    public static void main(String[] args) throws Exception{
            StackBenchmark benchmark = new StackBenchmark();
        benchmark.run();
    }


    public void run() throws Exception{
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new StackBenchmarkTask()).start();
        }
        latch.await();

        System.out.println("Stack consume Time: "+(System.currentTimeMillis()-beginTime)+" ms");

        latch = new CountDownLatch(THREAD_COUNT);
        barrier = new CyclicBarrier(THREAD_COUNT);
        beginTime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new ConcurrentStackBenchmarkTask()).start();
        }
        latch.await();
        System.out.println("ConcurrentStack consume Time: "+(System.currentTimeMillis()-beginTime)+" ms");
    }

    class StackBenchmarkTask implements Runnable{
        @Override
        public void run() {
            try {
                barrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                stack.push(Thread.currentThread().getName());
                stack.pop();
            }
            latch.countDown();
        }
    }


    class ConcurrentStackBenchmarkTask implements Runnable{

        @Override
        public void run() {
            try {
                barrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }

            for (int i = 0; i < 10; i++) {
                concurrentStack.push(Thread.currentThread().getName());
                concurrentStack.pop();
            }

            latch.countDown();

        }
    }

}
