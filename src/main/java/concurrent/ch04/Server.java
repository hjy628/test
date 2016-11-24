package concurrent.ch04;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by hjy on 15-8-18.
 */
public class Server {
    private ThreadPoolExecutor executor;

    public Server() {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void executeTask(Task task){
        System.out.printf("Server: A new task has arrived\n");
        executor.execute(task);
        System.out.printf("Server: pool Size: %d\n",executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());
        System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());
    }

    public void endServer(){
        executor.shutdown();
    }

}
