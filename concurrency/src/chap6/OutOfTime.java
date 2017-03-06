package chap6;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-3-6.
 */
public class OutOfTime {

    public static void main(String[] args) throws Exception{
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(),1);
        TimeUnit.SECONDS.sleep(1);
        timer.schedule(new ThrowTask(),1);
        TimeUnit.SECONDS.sleep(5);
    }

    static class ThrowTask extends TimerTask{
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
