package chap7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hjy on 17-3-7.
 * 通过中断来取消
 */
public class PrimeProducer extends Thread{
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue){
        this.queue=queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!Thread.currentThread().isInterrupted()){
            try {
                queue.put(p = p.nextProbablePrime());
            }catch (InterruptedException e){
                /*允许线程退出*/
            }
        }
    }

    public void cancel(){
        interrupt();
    }
}
