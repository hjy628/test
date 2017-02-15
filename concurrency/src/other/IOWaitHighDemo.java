package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

/**
 * Created by hjy on 17-2-15.
 */
public class IOWaitHighDemo {

    private String fileName = "/tmp/iowait.log";

    private static int threadCount = Runtime.getRuntime().availableProcessors();

    private Random random = new Random();

    public static void main(String[] args) throws Exception{
        if (args.length==1){
            threadCount=Integer.parseInt(args[0]);
            IOWaitHighDemo demo = new IOWaitHighDemo();
            demo.runTest();
        }
    }




    private void runTest() throws Exception{
        File file = new File(fileName);
        file.createNewFile();
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Task()).start();
        }
    }

    class Task implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
                    StringBuilder stringBuilder = new StringBuilder("====begin====\n");
                    String threadName = Thread.currentThread().getName();
                    for (int i = 0; i < 100000; i++) {
                        stringBuilder.append(threadName);
                        stringBuilder.append("\n");
                    }

                    stringBuilder.append("====end====\n");
                    writer.write(stringBuilder.toString());
                    writer.close();
                    Thread.sleep(random.nextInt(10));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
