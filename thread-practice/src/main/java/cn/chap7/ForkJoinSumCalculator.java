package cn.chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * Created by hjy on 17-2-8.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;


    public static final long THRESHOLD = 10_000;    //不再将任务分解为子任务的数组大小

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD){
            return computeSequentially();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start+length/2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+length/2,end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult+rightResult;
    }


    private long computeSequentially(){
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum+=numbers[i];
        }
        return sum;
    }



    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static long measureSumPerf(Function<Long,Long> addr , long n)throws Exception{
        long fatest = Long.MAX_VALUE;
        for (int i = 0; i < 20; i++) {
            long start = System.nanoTime();
            long sum = addr.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: "+sum);
            if (duration<fatest) fatest = duration;
        }
        return fatest;
    }



    public static void main(String[] args) throws Exception{
        TimeUnit.SECONDS.sleep(20);
        System.out.println("ForkJoin sum done in: " + measureSumPerf(ForkJoinSumCalculator::forkJoinSum,1_000_000_00) + "msecs");
    }

}
