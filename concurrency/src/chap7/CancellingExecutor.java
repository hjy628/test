package chap7;

import java.util.concurrent.*;

/**
 * Created by hjy on 17-3-7.
 * 扩展了ThreadPoolExecutor 并通过改写newTaskFor使得CancellableTask可以创建自己的Future
 */
public class CancellingExecutor extends ThreadPoolExecutor{
    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellableTask)
            return ((CancellableTask<T>)callable).newTask();
        else
            return super.newTaskFor(callable);
    }
}
