package memorydemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-2-21.
 * JVM参数： -Xms128M -Xmx128M -Xmn64M   runMode=NOTUSE_OBJECTPOOL;    Execute summary: Round( 10) Thread PerRound(100) Object Factor( 10 ) Execute Time( 20206ms)
 * S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT
 85.86   0.00  86.84  16.41  65.02  67.21   2652    9.803   631    3.756   13.559
 *
 *
 * JVM参数： -Xms128M -Xmx128M -Xmn64M   runMode=USE_OBJECTPOOL;
 *  java.lang.OutOfMemoryError: Java heap space
 *
 */
public class ObjectPoolDemo {

    private static int executeTimes=10;
    private static int maxFactor=10;
    private static int threadCount=100;
    private static final int NOTUSE_OBJECTPOOL=1;
    private static final int USE_OBJECTPOOL=2;
 //   private static int runMode=NOTUSE_OBJECTPOOL;
    private static int runMode=USE_OBJECTPOOL;
    private static CountDownLatch latch = null;


    public static void main(String[] args) throws Exception{
        TimeUnit.SECONDS.sleep(20);
        if (args.length==1){
            runMode = Integer.parseInt(args[0]);
        }
        if (args.length==2){
            runMode = Integer.parseInt(args[0]);
            executeTimes = Integer.parseInt(args[1]);
        }
        if (args.length==3){
            runMode = Integer.parseInt(args[0]);
            executeTimes = Integer.parseInt(args[1]);
            maxFactor = Integer.parseInt(args[2]);
        }
         if (args.length==4){
            runMode = Integer.parseInt(args[0]);
            executeTimes = Integer.parseInt(args[1]);
            maxFactor = Integer.parseInt(args[2]);
            threadCount = Integer.parseInt(args[3]);
        }

        long beginTime = System.currentTimeMillis();

        Task task = new Task();

        for (int i = 0; i < executeTimes; i++) {
            System.out.println("Round: " + (i+1));
            latch = new CountDownLatch(threadCount);
            for (int j = 0; j < threadCount; j++) {
                new Thread(task).start();
            }
            latch.await();
        }


        long endTime = System.currentTimeMillis();

        System.out.println("Execute summary: Round( " + executeTimes + ") Thread PerRound(100) Object Factor( " +
                maxFactor+ " ) Execute Time( " + (endTime - beginTime) + "ms)");

        TimeUnit.SECONDS.sleep(3);
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < maxFactor; i++) {
                if (runMode==USE_OBJECTPOOL){
                    BigObectPool.getInstance().getBigObject(i);
                }else {
                    new BigObject(i);
                }
            }
            latch.countDown();
        }
    }

    static class BigObectPool{

        private static final BigObectPool self = new BigObectPool();

        private final Map<Integer,BigObject> cacheObjects = new HashMap<Integer,BigObject>();

        public BigObectPool() {
            ;
        }

        public static BigObectPool getInstance(){
            return self;
        }

        public BigObject getBigObject(int factor) {
            if (cacheObjects.containsKey(factor)){
                return cacheObjects.get(factor);
            }else {
                BigObject object = new BigObject(factor);
                cacheObjects.put(factor,object);
                return object;
            }
        }
    }


    static class BigObject{
        private byte[] bytes = null;

        public BigObject(int factor) {
            bytes = new byte[(factor+1)*1024*1024];
        }

        public byte[] getBytes() {
            return bytes;
        }
    }

}
