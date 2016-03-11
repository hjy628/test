package concurrent.ch02;

import tt.Main;

/**
 * Created by hjy on 15-8-17.
 */
public class Writer implements Runnable{
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("Writer:Attempt to modify the prices.\n");
            pricesInfo.setPrice(Math.random()*10,Math.random()*8);
            System.out.printf("Writer: Price have been modified.\n");
            try {
                Thread.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
