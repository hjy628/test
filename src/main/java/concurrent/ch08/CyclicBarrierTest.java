package concurrent.ch08;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by hjy on 16-2-24.
 */
public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(3);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                }catch (Exception e){

                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        }catch (Exception e){

        }
        System.out.println(2);
    }
}
