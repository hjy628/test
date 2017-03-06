package chap5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hjy on 17-3-6.
 * 避免了方法同步带来的串行性
 * 但是当两个线程同时调用compute时存在一个漏洞，可能会导致计算得到相同的值，
 * 如果某个线程启动了一个开销很大的计算，而其他线程并不知道这个计算正在进行，很可能重复这个计算
 */
public class Memoizer2<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A,V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result==null){
            result  = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
