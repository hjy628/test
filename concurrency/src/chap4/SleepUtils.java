package chap4;

import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-5-3.
 */
public class SleepUtils {
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
