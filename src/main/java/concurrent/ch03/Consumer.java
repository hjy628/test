package concurrent.ch03;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by hjy on 15-8-18.
 */
public class Consumer implements Runnable{
    private List<String> buffer;

    private final Exchanger<List<String>> exchanger;

    public Consumer(Exchanger<List<String>> exchanger, List<String> buffer) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int cycle=1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Consumer: Cycle %d \n",cycle);
            try {
                buffer=exchanger.exchange(buffer);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.printf("Consumer: "+buffer.size()+"\n");
            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.printf("Consumer: ",message+"\n");
                buffer.remove(0);
            }
            cycle++;
        }
    }
}
