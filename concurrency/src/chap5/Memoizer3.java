package chap5;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by hjy on 17-3-6.
 * 首先检查某个相应的计算是否已经开始而不是如Memoizer2计算是否已经完成
 * 仍然有两个线程计算出相同值的漏洞，发生概率远小于Memoizer2中发生的概率，由于compute方法中的if代码块
 * 仍然是非原子的“先检查再执行”操作，因此两个线程仍有可能在同一时间调用comute来计算相同的值,
 * 即二者都没有在缓存中找到期望的值
 * 原因在于，复合操作“若没有则添加”是在底层的map对象上执行的，而这个对象无法通过加锁来确保原子性
 */
public class Memoizer3<A,V> implements Computable<A,V> {

    private final Map<A,Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A,V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }


    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f==null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws InterruptedException {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f=ft;
            cache.put(arg,ft);
            ft.run();   //在这里将调用c.compute
        }try {
            return f.get();
        }catch (ExecutionException e){
           e.printStackTrace();
            return null;
        }
        }
}
