package concurrent.ch03;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by hjy on 15-8-18.
 */
public class Producer implements Runnable{
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Producer(Exchanger<List<String>> exchanger, List<String> buffer) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int cycle=1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Producer: Cycle %d\n",cycle);
            for (int j = 0; j < 10; j++) {
                String message="Event "+((i*10)+j);
                System.out.printf("Producer: %s\n",message);
                buffer.add(message);
            }
            try {
                buffer=exchanger.exchange(buffer);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.printf("Producer: "+buffer.size()+"\n");
            cycle++;

        }
    }
}
