package concurrent.ch02;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 15-6-26.
 */
public class Main2_4 {
    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Thread thread1 = new Thread(producer);
        Consumer consumer = new Consumer(storage);
        Thread thread2 = new Thread(consumer);
        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread1.start();
    }
}
