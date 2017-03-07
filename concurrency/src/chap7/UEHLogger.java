package chap7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hjy on 17-3-7.
 *
 * 在运行时间较长的应用程序中，通常会为所有线程的未捕获异常指定同一个异常处理器，并且该处理器至少会将异常信息记录到日志中
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE,"Thread terminated with exception: "+ t.getName(),e);
    }
}
