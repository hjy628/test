package concurrent.ch01;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 15-6-25.
 */
public class Task1_13 implements Runnable{
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
