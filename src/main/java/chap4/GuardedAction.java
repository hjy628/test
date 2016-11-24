package chap4;

import java.util.concurrent.Callable;

/**
 * Created by hjy on 16-1-10.
 */
public abstract class GuardedAction<V> implements Callable<V> {
    protected final Predicate guard;

    public GuardedAction(Predicate guard) {
        this.guard = guard;
    }
}
