package chap4;

/**
 * Created by hjy on 17-5-5.
 */
public class Job implements Runnable{
    @Override
    public void run() {
        System.out.println("do job.....");
    }
}
