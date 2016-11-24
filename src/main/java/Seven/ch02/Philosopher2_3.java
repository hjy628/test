package Seven.ch02;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hjy on 15-8-31.
 */
public class Philosopher2_3 extends Thread{
    private ReentrantLock leftChopstick,rightChopstick;
    private Random random;

    public Philosopher2_3(ReentrantLock leftChopstick,ReentrantLock rightChopstick){
        this.leftChopstick=leftChopstick;this.rightChopstick=rightChopstick;
        random=new Random();
    }

    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(random.nextInt(1000));//思考一段时间
                leftChopstick.lock();
                try {
                    if (rightChopstick.tryLock(1000, TimeUnit.MILLISECONDS)){
                        //取右手边的筷子
                        try {
                            Thread.sleep(random.nextInt(1000));//进餐一段时间
                        }finally {
                            rightChopstick.unlock();
                        }
                    }else {
                        //没有取到右手边的筷子，放弃并继续思考
                    }
                }finally {
                    leftChopstick.unlock();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
