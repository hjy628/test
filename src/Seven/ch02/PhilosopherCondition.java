package Seven.ch02;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 15-8-31.
 */
public class PhilosopherCondition extends Thread{
    private boolean eating;
    private PhilosopherCondition left;
    private PhilosopherCondition right;
    private ReentrantLock table;
    private Condition condition;
    private Random random;

    public PhilosopherCondition (ReentrantLock table){
        eating = false;
        this.table=table;
        condition = table.newCondition();
        random = new Random();
    }

    public void setLeft(PhilosopherCondition left) { this.left = left; }

    public void setRight(PhilosopherCondition right) { this.right = right; }

    @Override
    public void run() {
        try {
            while (true){
             think();
                eat();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void think() throws InterruptedException{
        table.lock();
        try {
            eating=false;
            left.condition.signal();
            right.condition.signal();
        }finally {
            table.unlock();
        }
        Thread.sleep(1000);
    }

    public void eat()throws InterruptedException{
        table.lock();
        try {
            while (left.eating||right.eating)
                condition.await();
            eating =true;
        }finally {
            table.unlock();
        }
        Thread.sleep(1000);
    }
}
