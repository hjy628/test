package concurrent.ch05;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 16-2-25.
 */
public class FairAndUnfairTest {
    private static ReentrantLock2 fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);

    @Test
    public void fair(){
        testLock(fairLock);
    }

    @Test
    public void unfair(){
        testLock(unfairLock);
    }

    private void testLock(ReentrantLock2 lock){
        for (int i = 0; i < 5; i++) {
            //启动5个job
            new Job(lock).start();
        }
    }

    private static class Job extends Thread{
        private ReentrantLock2 lock;

        public Job(ReentrantLock2 lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName());
            for (Thread thread:lock.getQueuedThreads()){
                System.out.print("[" + thread.getName() + "]");
            }
            System.out.println(currentThread().getName());
            for (Thread thread:lock.getQueuedThreads()){
                System.out.print("[" + thread.getName() + "]");
            }
        }
    }


    private static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        @Override
        protected Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }



}
