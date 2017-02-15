package other;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-2-15.
 */
public class SyHighDemo {
    private static int threadCount = 50000000;

    private Random random = new Random();

    private Object[] locks;


    public static void main(String[] args) throws Exception{

        TimeUnit.SECONDS.sleep(10);

        if(args.length==1){
            threadCount=Integer.parseInt(args[0]);
        }
        SyHighDemo demo = new SyHighDemo();
    }

    private void runTest() throws Exception{
        locks = new Object[threadCount];
        for (int i = 0; i < threadCount; i++) {
            locks[i] = new Object();
        }

        for (int i = 0; i < threadCount; i++) {
            new Thread(new ATask(i)).start();
            new Thread(new BTask(i)).start();
        }

    }

    class ATask implements Runnable{

        private Object lockObject = null;

        public ATask(int i) {
            this.lockObject = locks[i];
        }

        @Override
        public void run() {
            while (true){
                try {
                    synchronized (lockObject){
                        lockObject.wait(random.nextInt(10));
                    }
                }catch (Exception e){
                    ;
                }
            }
        }
    }

    class BTask implements Runnable{

        private Object lockObject = null;

        public BTask(int i) {
            this.lockObject = locks[i];
        }

        @Override
        public void run() {
            while (true){
                synchronized (lockObject){
                    lockObject.notifyAll();
                }
                try {
                Thread.sleep(random.nextInt(5));
                }catch (Exception e){
                  e.printStackTrace();
                }
            }
        }
    }



}
