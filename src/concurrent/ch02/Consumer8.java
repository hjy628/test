package concurrent.ch02;

import java.util.Random;

/**
 * Created by hjy on 15-8-17.
 */
public class Consumer8 implements Runnable{
    private Buffer buffer;

    public Consumer8(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()){
            String line = buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line){
        try {
            Random random=new Random();
            Thread.sleep(random.nextInt(100));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}
