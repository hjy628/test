package concurrent.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by hjy on 15-8-18.
 */
public class Main3_8 {
    public static void main(String[] args) {
        List<String> buffer1=new ArrayList<String>();
        List<String> buffer2=new ArrayList<String>();
        Exchanger<List<String>> exchanger=new Exchanger<List<String>>();
        Producer producer=new Producer(exchanger,buffer1);
        Consumer consumer=new Consumer(exchanger,buffer2);
        Thread threadProducer=new Thread(producer);
        Thread threadConsumer=new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();
    }
}
