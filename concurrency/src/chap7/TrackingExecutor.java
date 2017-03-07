package chap7;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-3-7.
 *
 * 在ExecutorService中跟踪在关闭之后被取消的任务
 *
 * 通过封装ExecutorService并使得execute(类似地还有submit,在这里没有给出)记录哪些任务是在关闭后取消的，
 * TrackingExecutor可以找出那些任务已经开始但还没有正常完成.在Executor结束后，getCancelledTasks返回被取消的任务清单.
 * 要使这项技术能发挥作用，任务在返回时必须维持线程的中断状态，在所有设计良好的任务中都会实现这个功能
 */
public class TrackingExecutor extends AbstractExecutorService{
    private final ExecutorService exec;
    private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }

    public List<Runnable> getCancelledTasks(){
        if (!exec.isTerminated()){
            throw new IllegalStateException("......");
        }
        return new ArrayList<Runnable>(tasksCancelledAtShutdown);
    }


    @Override
    public void shutdown() {
        exec.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout, unit);
    }

    @Override
    public void execute(final Runnable command) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    }finally {
                        if (isShutdown()&&Thread.currentThread().isInterrupted()){
                            tasksCancelledAtShutdown.add(command);
                        }
                    }
                }
            });
    }
}
