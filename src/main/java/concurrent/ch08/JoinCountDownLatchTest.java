package concurrent.ch08;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 16-2-24.
 */
public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException{
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("parser1 finish");
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");
            }
        });

        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");

    }
}
