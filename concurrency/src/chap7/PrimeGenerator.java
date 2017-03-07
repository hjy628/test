package chap7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-3-7.
 * 使用volatile类型的域来保存取消状态
 */
public class PrimeGenerator implements Runnable{

    private final List<BigInteger> primes = new ArrayList<BigInteger>();

    private volatile boolean cancelled;


    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled){
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<BigInteger>(primes);
    }


}
