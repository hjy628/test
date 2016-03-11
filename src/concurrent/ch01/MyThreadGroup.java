package concurrent.ch01;

/**
 * Created by hjy on 15-6-25.
 */
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name){
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The thread %s has thrown an Exception\n", t.getId());
        e.printStackTrace(System.out);
        System.out.printf("Terminating the rest of the THreads\n");
        interrupt();
    }
}
