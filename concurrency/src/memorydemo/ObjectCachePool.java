package memorydemo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hjy on 17-2-21.
 * 基于LinkedHashMap简单实现FIFO 和LRU 策略的CachePool
 */
public class ObjectCachePool<K,V> {

    public static final int FIFO_POLICY=1;

    public static final int LRU_POLICY=2;

    public static final int DEFAULT_SIZE=10;

    private Map<K,V> cacheObjects;

    public ObjectCachePool() {
        this(DEFAULT_SIZE);
    }

    public ObjectCachePool(final  int size) {
       this(size,FIFO_POLICY);
    }

    public ObjectCachePool(final  int size,final int policy) {
        switch (policy){
            case FIFO_POLICY:
                cacheObjects = new LinkedHashMap<K, V>(size){
                    private static final long serialVersionUID = 1L;

                    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
                        return size() > size;
                    }
                };
                break;
            case LRU_POLICY:
                cacheObjects = new LinkedHashMap<K, V>(size,0.75f,true){
                    private static final long serialVersionUID = 1L;

                    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
                        return size() > size;
                    }
                };
                break;
            default:
                throw new IllegalArgumentException("Unknown policy: "+policy);
        }
    }

    public void put(K key,V value){
        cacheObjects.put(key, value);
    }

    public V get(K key){
        return cacheObjects.get(key);
    }

    public void remove(K key){
        cacheObjects.remove(key);
    }

    public void clear(){
        cacheObjects.clear();
    }


}
