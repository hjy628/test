package chap7;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * Created by hjy on 17-3-7.
 * 扩展了Callable,并增加了一个cancel方法和一个newTask工厂方法来构建RunnableFuture
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();
}
