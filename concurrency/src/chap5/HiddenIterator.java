package chap5;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by hjy on 17-3-6.
 * addThenThings有可能抛出ConcurrentModificationException ,在生成调试消息的过程中，toString对容器进行迭代，
 * 容器的hashCode和equals等方法也会间接地执行迭代操作，当容器作为另一个容器的元素或键值时，会出现这种情况
 * 同样，containsAll,removeAll,retainAll等方法，以及把容器作为参数的构造函数，都会对容器进行迭代
 */
public class HiddenIterator {

    private final Set<Integer> set = new HashSet<Integer>();

    public synchronized void add(Integer i){set.add(i);}

    public synchronized void remove(Integer i){set.remove(i);}

    public void addThenThings() throws ConcurrentModificationException{
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(r.nextInt());
            System.out.println("DEBUG: added ten elements to "+set);    //会产生迭代操作
        }
    }

}
