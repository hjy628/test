package cn.test;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hjy on 17-2-13.
 */
public class ExecuteCaught {

    public static void main(String[] args){
        /*class ExceptionHandler implements Thread.UncaughtExceptionHandler
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                System.out.println("==Exception: "+e.getMessage());
            }
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        Thread thread = new Thread(new NoCaughtThread.Task());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        exec.execute(thread);
        exec.shutdown();*/

        class ExceptionHandler implements Thread.UncaughtExceptionHandler
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                System.out.println("==Exception: "+e.getMessage());
            }
        }


        class ThreadPoolTask implements Runnable
        {
            @Override
            public void run()
            {
                Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
                System.out.println(3/2);
                System.out.println(3/0);
                System.out.println(3/1);
            }
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(new NoCaughtThread.Task());
        exec.shutdown();

        ExecutorService exec1 = Executors.newCachedThreadPool();
        exec1.submit(new ThreadPoolTask());
        exec1.shutdown();


    }

}
