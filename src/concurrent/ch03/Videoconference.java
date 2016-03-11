package concurrent.ch03;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hjy on 15-8-17.
 */
public class Videoconference implements Runnable{
    private final CountDownLatch controller;

    public Videoconference(int number) {
        controller = new CountDownLatch(number);
    }

    @Override
    public void run() {
        System.out.printf("VideoConference: Waiting for %d participants.\n",controller.getCount());
        try {
            controller.await();
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void arrive(String name){
        System.out.printf("%s has arrived.",name);
        controller.countDown();
        System.out.printf("VideoConference: waiting for %d participants.\n",controller.getCount());
    }



}
