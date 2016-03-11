package concurrent.ch02;

/**
 * Created by hjy on 15-6-26.
 */
public class Job implements Runnable{
    private PrintQueue printQueue;
    public Job(PrintQueue printQueue){
        this.printQueue=printQueue;
    }
    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n",Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been priented\n",Thread.currentThread().getName());
    }
}
