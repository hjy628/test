package chap5;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by hjy on 17-3-6.
 * 当缓存的是Future而不是值时，将导致缓存污染(Cache Pollution)问题,如果某个计算被取消或者失败，那么在计算
 * 这个结果时将指明计算过程被取消或者失败。为了避免这种情况，如果Memoizer发现计算被取消，那么将把Future从缓存中移除，如果检测到RuntimeException,
 * 那么也会移除Future,这样将来计算才可能成功。Memoizer同样没有解决缓存逾期的问题，但它可以通过使用FutureTask的子类来解决，
 * 在子类中为每个结果指定一个逾期时间，并定期扫描缓存中逾期的元素。
 */
public class Memoizer<A,V> implements Computable<A,V> {

    private final Map<A,Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A,V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(final A arg) throws InterruptedException {
            while (true){
                Future<V> f = cache.get(arg);
                if (f==null){
                    Callable<V> eval = new Callable<V>() {
                        @Override
                        public V call() throws Exception {
                            return c.compute(arg);
                        }
                    };
                    FutureTask<V> ft = new FutureTask<V>(eval);
                    f = cache.putIfAbsent(arg,ft);
                    if (f==null){f=ft;ft.run();}
                }
                try {
                    return f.get();
                }catch (CancellationException e){
                    cache.remove(arg,f);
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
        }
}
