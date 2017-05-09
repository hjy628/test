package chap5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 17-5-8.
 */
public class FairAndUnfairTest {
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(true);

    @Test
    public void fair(){
        testLock(fairLock);
    }

    @Test
    public void unfair(){
        testLock(unfairLock);
    }

    private void testLock(Lock lock){
        //启动5个Job
        for (int i = 0; i < 5; i++) {
            new Job(lock).start();
        }
    }


    private static class Job extends Thread{
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            //连续两次打印当前的THread和等待队列中的Thread
            ReentrantLock2 locks = (ReentrantLock2)lock;
            System.out.println(Thread.currentThread().getId()+"----"+locks.getQueuedThreads());
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
