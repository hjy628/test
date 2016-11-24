package test;

import java.util.Calendar;
import java.util.concurrent.*;

/**
 * Created by hjy on 16-2-24.
 */
public class ObtainBigger {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        //需要查找最大数的数组
        Double data[] = new Double[]{1910.32,517.98,987.66,326.13};
        //获取前两个里最大的
        BiggerCallable c1=new BiggerCallable(data[0],data[1]);
        Future<Double> bigger1 = executorService.submit(c1);
         //获取后两个里最大的
        BiggerCallable c2=new BiggerCallable(data[2],data[3]);
        Future<Double> bigger2 = executorService.submit(c2);
        //获取两个结果中较大的，这时会阻塞，只有前面两个结果都返回时才会往下进行
        BiggerCallable c=new BiggerCallable(bigger1.get(),bigger2.get());
        Future<Double> bigger = executorService.submit(c);
        //输出结果
        System.out.println(bigger.get());
        executorService.shutdown();
    }


    private static class BiggerCallable implements java.util.concurrent.Callable{
        Double d1,d2;

        public BiggerCallable(Double d1, Double d2) {
            this.d1 = d1;
            this.d2 = d2;
        }

        @Override
        public Object call() throws Exception {
            return d1<d2?d1:d2;
        }
    }
}
