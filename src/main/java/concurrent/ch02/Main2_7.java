package concurrent.ch02;

/**
 * Created by hjy on 15-6-26.
 */
public class Main2_7 {
    public static void main(String[] args) {
        PrintQueue7 printQueue = new PrintQueue7();
        Thread thread[] = new Thread[10];
        for (int i=0;i<10;i++){
            thread[i]=new Thread(new Job7(printQueue),"Thread " + i);
        }
        for (int i=0;i<10;i++){
            thread[i].start();
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
