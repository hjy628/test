package concurrent.ch03;

import java.util.concurrent.Phaser;

/**
 * Created by hjy on 15-8-18.
 */
public class Main3_6 {
    public static void main(String[] args) {
        Phaser phaser=new Phaser(3);
        FileSearch system = new FileSearch("/home/hjy","log",phaser);
        FileSearch apps = new FileSearch("/home/hjy/local","log",phaser);
        FileSearch documents = new FileSearch("/home/hjy/soft","log",phaser);

        Thread systemThread=new Thread(system,"System");
        systemThread.start();
        Thread appsThread=new Thread(apps,"Apps");
        appsThread.start();
        Thread documentsThread=new Thread(documents,"Documents");
        documentsThread.start();

        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.printf("Terminated: "+phaser.isTerminated());
    }
}
