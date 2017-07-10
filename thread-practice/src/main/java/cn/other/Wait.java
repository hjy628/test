package cn.other;

import java.util.Date;

/**
 * Created by hjy on 17-7-10.
 */
public class Wait {


    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (Wait.class) {
                try {
                    System.out.println(new Date() + " Thread1 is running");
                    Wait.class.wait();
                    System.out.println(new Date() + " Thread1 ended");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (Wait.class) {
                try {
                    System.out.println(new Date() + " Thread2 is running");
                    Wait.class.notify();
                    // Don't use sleep method to avoid confusing
                    for(long i = 0; i < 200000; i++) {
                        for(long j = 0; j < 100000; j++) {}
                    }
                    System.out.println(new Date() + " Thread2 release lock");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            for(long i = 0; i < 200000; i++) {
                for(long j = 0; j < 100000; j++) {}
            }
            System.out.println(new Date() + " Thread2 ended");
        });

        // Don't use sleep method to avoid confusing
        for(long i = 0; i < 200000; i++) {
            for(long j = 0; j < 100000; j++) {}
        }
        thread2.start();
    }

}
