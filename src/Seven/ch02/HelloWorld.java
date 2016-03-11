package Seven.ch02;

/**
 * Created by hjy on 15-8-18.
 */
public class HelloWorld {
    public static void main(String[] args) throws InterruptedException{
        Thread myThread = new Thread(){
            @Override
            public void run() {
                System.out.println("Hello from new thread");
            }
        };
        myThread.start();
        Thread.yield();
        //Thread.sleep(tt);
        System.out.println("Hello from main thread");
        myThread.join();
    }
}
