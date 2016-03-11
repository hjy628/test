package concurrent.ch01;

/**
 * Created by hjy on 15-6-25.
 */
public class Main1_13 {
    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        Task1_13 task1_13 = new Task1_13();
        Thread thread;
        System.out.printf("Starting the Threads\n");
        for (int i=0;i<10;i++){
            thread=factory.newThread(task1_13);
            thread.start();
        }
        System.out.printf("Factory status:\n");
        System.out.printf("%s\n",factory.getStatus());
    }
}
