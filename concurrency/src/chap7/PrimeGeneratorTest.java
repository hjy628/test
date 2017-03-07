package chap7;

import java.math.BigInteger;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by hjy on 17-3-7.
 */
public class PrimeGeneratorTest {

    public static void main(String[] args) throws Exception{
       PrimeGeneratorTest demo = new PrimeGeneratorTest();
    List<BigInteger>  result =   demo.aSecondOfPrimes();
        for (BigInteger big:
             result) {
            System.out.println(big.toString());
        }
    }


    List<BigInteger> aSecondOfPrimes() throws InterruptedException{
            PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            SECONDS.sleep(1);
        }finally {
            generator.cancel();
        }
        generator.cancel();
        return generator.get();
    }

}
