package chap8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hjy on 17-3-9.
 * 定制Thread基类
 *
 * 定制其他行为，包括：为线程指定名字，设置自定义UncaughtExceptionHandler向Logger中写入信息，维护一些统计信息（包括有多少线程被创建和销毁）
 * 以及在线程被创建或者终止时把调试信息写入日志
 */
public class MyAppThread extends Thread{

    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();

    public MyAppThread(Runnable target) {
        this(target,DEFAULT_NAME);
    }

    public MyAppThread(Runnable target, String name) {
        super(target, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.log(Level.SEVERE,"UNCAUGHT in thread "+t.getName(),e);
            }
        });
    }


    @Override
    public void run() {
        //复制debug标志以确保一致的值
        boolean debug = debugLifecycle;
        if (debug) log.log(Level.FINE," Created "+getName());
        try {
            alive.incrementAndGet();
            super.run();
        }finally {
            alive.decrementAndGet();
            if (debug) log.log(Level.FINE," Exiting "+getName());
        }
    }

    public static int getThreadsCreated(){return created.get();}
    public static int getThreadsAlive(){return alive.get();}
    public static boolean getDebug(){return debugLifecycle;}
    public static void setDebug(boolean b){debugLifecycle = b;}

}
