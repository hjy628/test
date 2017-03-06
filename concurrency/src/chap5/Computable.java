package chap5;

/**
 * Created by hjy on 17-3-6.
 */
public interface Computable<A,V> {
    V compute(A arg)throws InterruptedException;
}
