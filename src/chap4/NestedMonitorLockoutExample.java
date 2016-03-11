package chap4;


import sun.security.util.Debug;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * Created by hjy on 16-1-10.
 */
public class NestedMonitorLockoutExample {
    public static void main(String[] args) {
        final Helper helper = new Helper();
        System.out.println("Before calling guaredMethod.");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String result;
                result = helper.xGuarededMethod("test");
                System.out.println(result);
            }
        });
        t.start();

        final Timer timer = new Timer();

        //延迟50ms调用helper.stateChanged方法
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                helper.xStateChanged();
                timer.cancel();
            }
        },5,10);
    }

    private static class Helper{
        private volatile boolean isStateOk = false;
        private final Predicate stateBeOk = new Predicate() {
            @Override
            public boolean evaluate() {
                return isStateOk;
            }
        };
    private final Blocker blocker = new ConditionVarBlocker();

    public synchronized String xGuarededMethod(final String message){
        GuardedAction<String> ga = new GuardedAction<String>(stateBeOk) {
            @Override
            public String call() throws Exception {
                return message + "->received.";
            }
        };
        String result = null;
        try {
            result = blocker.callWithGuard(ga);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public synchronized void xStateChanged(){
        try {
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    isStateOk = true;
                    System.out.println("state ok.");
                    return Boolean.TRUE;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    }
}
