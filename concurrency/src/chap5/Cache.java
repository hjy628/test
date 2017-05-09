package chap5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by hjy on 17-5-8.
 */
public class Cache {
    static Map<String,Object> map = new HashMap<String,Object>();
    static ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();
    static Lock r = rw1.readLock();
    static Lock w = rw1.writeLock();

    //获取一个key对应的val
    public static final Object get(String key){
        r.lock();
        try {
            return map.get(key);
        }finally {
            r.unlock();
        }
    }

    //设置Key对应的value,并返回旧的value
    public static final Object put(String key,Object value){
        w.lock();
        try {
            return map.put(key, value);
        }finally {
            w.unlock();
        }
    }

    //清空所有的内容
    public static final void clear(){
        w.lock();
        try {
            map.clear();
        }finally {
            w.unlock();
        }
    }


}
