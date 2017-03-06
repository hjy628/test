package chap5;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjy on 17-3-6.
 * 需要同步来确保两个线程不会同时访问HashMap,但每次只有一个线程能够执行compute
 */
public class Memoizer1<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<A, V>();
    private final Computable<A,V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result==null){
            result  = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
