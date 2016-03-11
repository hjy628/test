package concurrent.ch02;

/**
 * Created by hjy on 15-6-26.
 */
public class Main2_5 {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread thread[] = new Thread[10];
        for (int i=0;i<10;i++){
            thread[i]=new Thread(new Job(printQueue),"Thread " + i);
        }
        for (int i=0;i<10;i++){
            thread[i].start();
        }
    }
}
