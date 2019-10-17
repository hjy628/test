package chap1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 18-3-5.
 *
 *  -Xms4g -Xmx4g -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -Xmn512m
 *  Digested 5017 pigs in 594644 ms.
 *  8.446
 *
 *  -Xms2g -Xmx2g -XX:+UseParallelGC  -Xmn1536m
 *  Digested 5009 pigs in 547476 ms.
 *  9.157
 *
 */
public class PigInThePython {

    static volatile List pigs = new ArrayList();
    static volatile int pigsEaten = 0;
    static final int ENOUGH_PIGS = 5000;

    public static void main(String[] args) throws InterruptedException{
        new PigEater().start();
        new PigDigester().start();
    }


    static class PigEater extends Thread{
        @Override
        public void run() {
            while (true){
                pigs.add(new byte[32*1024*1024]);   //32MB per pig
                if (pigsEaten>ENOUGH_PIGS)   return;;
                takeANap(100);
            }

        }
    }

    static class PigDigester extends Thread{
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            while (true){
                takeANap(2000);
                pigsEaten += pigs.size();
                pigs = new ArrayList();
                System.out.println(pigsEaten);
                if (pigsEaten>ENOUGH_PIGS){
                    System.out.format("Digested %d pigs in %d ms. %n",pigsEaten,System.currentTimeMillis()-start);
                    return;
                }
            }
        }
    }


    static void takeANap(int ms){
        try {
            Thread.sleep(ms);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
