package other;

import java.util.LinkedList;

/**
 * Created by hjy on 17-2-14.
 */
public class NoSemaphoreExample {

    int maxActive;
    int numActive;
    int maxWait;
    LinkedList pool;

    public void get()throws Exception{
        long startTime = System.currentTimeMillis();
        Object object = null;
        for (;;){
            synchronized (this){
                object = pool.removeFirst();
                if ((object==null)&&(numActive>=maxActive)){
                    long waitTime = maxWait - (System.currentTimeMillis() - startTime);
                    wait(waitTime);
                    if ((System.currentTimeMillis()-startTime)>maxWait){
                        //抛出超时异常
                    }else {
                        continue;
                    }
                }
                numActive++;
            }
            //if needed then create object & validate object
        //    return object;
        }
    }

}
