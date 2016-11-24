package concurrent.ch02;

/**
 * Created by hjy on 15-6-26.
 */
public class Producer implements Runnable{
    private EventStorage storage;
    public Producer(EventStorage storage){
       this.storage = storage;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            storage.set();
        }
    }
}
