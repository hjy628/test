package cn.chap4;

/**
 * Created by hjy on 16-12-19.
 * 线程安全
 */
public final class Counter {
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment(){
        if (value==Long.MAX_VALUE)
            throw new IllegalStateException("counter overflow");
            return ++value;
    }


}
