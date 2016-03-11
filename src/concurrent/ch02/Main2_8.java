package concurrent.ch02;

/**
 * Created by hjy on 15-8-17.
 */
public class Main2_8 {
    public static void main(String[] args) {
        FileMock mock = new FileMock(100,10);
        Buffer buffer=new Buffer(20);
        Producer8 producer8=new Producer8(mock,buffer);
        Thread threadProducer = new Thread(producer8,"Producer");

        Consumer8 consumers[]=new Consumer8[3];
        Thread threadConsumers[]=new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i]=new Consumer8(buffer);
            threadConsumers[i]=new Thread(consumers[i],"Consumer "+ i);
        }

        threadProducer.start();
        for (int i = 0; i < 3; i++) {
            threadConsumers[i].start();
        }

    }
}
