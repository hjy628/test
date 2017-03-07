package chap7;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hjy on 17-3-7.
 * 不可靠的取消操作将把生产者置于阻塞的操作中（不可这样做）
 * 生产者线程生成素数，并将它们放入一个阻塞队列，如果生产者的速度超过了消费者的处理速度，队列将被填满，put方法也会阻塞，当生产者在put方法中阻塞时，
 * 如果消费者希望取消生产者任务，那么将发生什么情况？它可以调用cancel方法来设置cancelled标志，但此时生产者却永远不能检查这个标志，
 * 因为它无法从阻塞的put方法中恢复过来（因为消费者此时已经停止从队列中取出素数，所以put方法将一直保持阻塞状态）
 *
 * 每个线程都有一个boolean
 */
public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancellled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue){
        this.queue=queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancellled){
         try {
             queue.put(p=p.nextProbablePrime());
         }catch (InterruptedException consumed){}
        }
    }

    public void cancel(){
        cancellled = true;
    }

    void consumePrimes() throws InterruptedException{
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<BigInteger>(100);
        BrokenPrimeProducer primeProducer = new BrokenPrimeProducer(primes);
        primeProducer.start();
        try {
          //  while (needMorePrimes){
              //  consume(primes.take());
        //    }
        }finally {
            primeProducer.cancel();
        }
    }


}
