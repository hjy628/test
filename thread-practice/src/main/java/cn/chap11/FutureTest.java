package cn.chap11;

import java.util.concurrent.*;

/**
 * Created by hjy on 17-2-9.
 */
public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                System.out.println("开始耗时操作!");
                TimeUnit.SECONDS.sleep(9);
                System.out.println("结束耗时操作!");
                return 200D;
            }
        });

        System.out.println("开始其他的事情!");

        try {
            Double result = future.get(10,TimeUnit.SECONDS);
            System.out.println(result);
            executor.shutdownNow();
        }catch (ExecutionException ee){
            //计算抛出一个异常
            ee.printStackTrace();
        }catch (InterruptedException ie){
            //当前线程在等待过程中被中断
            ie.printStackTrace();
        }catch (TimeoutException te){
            // 在Future对象完成之前超过已过期
            te.printStackTrace();
        }

    }

}
