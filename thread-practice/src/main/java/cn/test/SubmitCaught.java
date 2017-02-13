package cn.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hjy on 17-2-13.
 */
public class SubmitCaught {

    public static void main(String[] args)
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> future = exec.submit(new NoCaughtThread.Task());
        exec.shutdown();
        try
        {
            future.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            System.out.println("==Exception: "+e.getMessage());
        }
    }


}
