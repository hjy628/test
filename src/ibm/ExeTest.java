package ibm;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 16-3-4.
 */
public class ExeTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new FetchTask("test"));
        Thread thread2 = new Thread(new FetchTask("test"));
        Thread thread3 = new Thread(new FetchTask("test"));

      //  final Queue<FetchTask> tasks = new BlockingQueue<FetchTask>();

     //   ExecutorService executorService = new TrackingThreadPool(10, 100, 100, TimeUnit.MILLISECONDS, tasks);


    }
}
