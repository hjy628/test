package chap5;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-3-6.
 */
public class ExpensiveFunction implements Computable<String,BigInteger>{
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new BigInteger(arg);
    }
}
