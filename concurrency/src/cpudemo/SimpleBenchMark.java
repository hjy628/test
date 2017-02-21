package cpudemo;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by hjy on 17-2-21.
 */
public class SimpleBenchMark {

    private CountDownLatch latch = new CountDownLatch(1000000);

    private Random random = new Random();

    public static void main(String[] args) throws Exception{
        SimpleBenchMark benchMark = new SimpleBenchMark();
        benchMark.runBenchmark();
    }


    public void runBenchmark() throws Exception{
        long beginTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(400);
        for (int i = 0; i < 1000000; i++) {
            executor.execute(new Handler());
        }
        latch.await();
        System.out.println("Consume Time: " + (System.currentTimeMillis()-beginTime) + "ms");
        executor.shutdown();
    }

    class Handler implements Runnable{
        @Override
        public void run() {
            BlockingQueue<String> resultQueue = new ArrayBlockingQueue<String>(1);
            try {
                resultQueue.poll(random.nextInt(10)+100, TimeUnit.MILLISECONDS);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            latch.countDown();
        }
    }

}
