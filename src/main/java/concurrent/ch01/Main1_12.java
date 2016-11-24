package concurrent.ch01;

/**
 * Created by hjy on 15-6-25.
 */
public class Main1_12 {
    public static void main(String[] args) {
        MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
        Task12 task12 = new Task12();
        for (int i=0;i<2;i++){
            Thread t = new Thread(threadGroup,task12);
            t.start();
        }
    }
}
