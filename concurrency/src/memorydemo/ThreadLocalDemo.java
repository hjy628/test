package memorydemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-2-21.
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws Exception{
        TimeUnit.SECONDS.sleep(20);
        System.out.println("start");
        ThreadLocalDemo demo = new ThreadLocalDemo();
        demo.run();
    }

    public void run() throws Exception{
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new Task());
        TimeUnit.SECONDS.sleep(10);
        System.out.println("start gc");
        System.out.println("start gc");
        System.gc();
        System.out.println("end gc");


    }

    class Task implements Runnable{
        @Override
        public void run() {
            ThreadLocal<byte[]> localString = new ThreadLocal<byte[]>();
            localString.set(new byte[1024*1024*30]);

            System.out.println("start free");
         //   localString.remove();
        }
    }
}
