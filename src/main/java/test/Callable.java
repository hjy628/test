package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 16-2-24.
 */
public class Callable {
    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newCachedThreadPool();
        java.util.concurrent.Callable callableTask = new java.util.concurrent.Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("do task");
                return "ok";
            }
        };
        Future<String> future = executor.submit(callableTask);
        System.out.println("after submit task");
        String result = future.get();
        System.out.println("after future.get()");
        System.out.println("result="+result);
        executor.shutdown();

    }
}
