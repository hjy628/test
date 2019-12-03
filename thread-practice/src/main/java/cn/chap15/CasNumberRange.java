package cn.chap15;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hjy on 17-8-29.
 * 通过CAS来维持包含多个变量的不变性条件
 */
public class CasNumberRange {
    private static class IntPair{
        final int lower;    //不变性条件： lower <= upper
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values = new AtomicReference<IntPair>(new IntPair(0,0));

    public int getLower(){
        return values.get().lower;
    }

    public int getUpper(){
        return values.get().upper;
    }

    public void setLower(int i){
        while (true){
            IntPair oldv = values.get();
            if (i>oldv.upper)
                throw new IllegalArgumentException("Can't set lower to "+ i +" > upper");
            IntPair newv = new IntPair(i,oldv.upper);
            if (values.compareAndSet(oldv,newv))
                return;
        }
    }

    public void setUpper(int i){
        while (true){
            IntPair oldv = values.get();
            if (i<oldv.lower)
                throw new IllegalArgumentException("Can't set upper to "+ i +" < lower");
            IntPair newv = new IntPair(oldv.lower,i);
            if (values.compareAndSet(oldv,newv))
                return;
        }
    }

}
