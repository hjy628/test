package threadandlock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 17-2-21.
 * 资源消耗并不高,但性能下降很多 Execute summary: Round(10) ThreadCount Per Round (800) Execute Time( 36733ms)
 */
public class LockHotDemo {

    private static CountDownLatch latch;

    private static int threadCount = Runtime.getRuntime().availableProcessors()*100;

    private static int executeTimes = 10;

    public static void main(String[] args) throws Exception{
        if ((args.length==1)||(args.length==2)){
            threadCount = Integer.parseInt(args[0]);
        }
        if (args.length==2){
            executeTimes = Integer.parseInt(args[1]);
        }

        HandleTask task = new HandleTask();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < executeTimes; i++) {
            System.out.println("Round: "+(i+1));
            latch = new CountDownLatch(threadCount);
            for (int j = 0; j < threadCount; j++) {
                new Thread(task).start();
            }
            latch.await();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Execute summary: Round(" +executeTimes+") ThreadCount Per Round ("
        +threadCount+") Execute Time( "+(endTime-beginTime)+"ms)" );
    }


    static class HandleTask implements Runnable{

        private Random random = new Random();

        @Override
        public void run() {
            Handler.getInstance().handle(random.nextInt(10000));
            latch.countDown();
        }
    }

    static class Handler{
        private static final Handler self = new Handler();

        private final Random random = new Random();

        private final Lock lock = new ReentrantLock();

        public Handler() {
            ;
        }

        public static Handler getInstance(){
            return self;
        }

        public void handle(int id){
            try {
                lock.lock();;
                //execute sth
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(10));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }

        }

    }
}
