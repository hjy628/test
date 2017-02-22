package cpunouseeffective;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-2-22.
 * 未充分使用cpu
 * 原因主要是在能并行处理的场景中未使用足够的线程
 * Execute summary: Round(10) ThreadCount Per Round (200) Execute Time( 9231ms)
 *
 * 重构后
 * Execute summary: Round(10) ThreadCount Per Round (200) Execute Time( 1453ms)
 *
 */
public class CPUNotUseEffectiveDemo {

    private static int executeTimes = 10;

    private static int taskCount = 200;
    //重构后
    private static CountDownLatch latch;

    private static final int TASK_THREADCOUNT = Runtime.getRuntime().availableProcessors();




/*
//未充分利用

    public static void main(String[] args) throws Exception{
        if ((args.length==1)||(args.length==2)){
            taskCount = Integer.parseInt(args[0]);
        }
        if (args.length==2){
            executeTimes = Integer.parseInt(args[1]);
        }
        Task task = new Task();
        for (int i = 0; i < taskCount; i++) {
            task.addTask(Integer.toString(i));
        }

        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < executeTimes; i++) {
            System.out.println("Round: "+(i+1));
           Thread thread = new Thread(task);
            thread.start();
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Execute summary: Round(" +executeTimes+") ThreadCount Per Round ("
                +taskCount+") Execute Time( "+(endTime-beginTime)+"ms)" );

    }
*/

    public static void main(String[] args) throws Exception{
        if ((args.length==1)||(args.length==2)){
            taskCount = Integer.parseInt(args[0]);
        }
        if (args.length==2){
            executeTimes = Integer.parseInt(args[1]);
        }
        Task[] tasks = new Task[TASK_THREADCOUNT];
        for (int i = 0; i < TASK_THREADCOUNT; i++) {
            tasks[i] = new Task();
        }

        for (int i = 0; i < taskCount; i++) {
            int mod = i%TASK_THREADCOUNT;
            tasks[mod].addTask(Integer.toString(i));
        }
        long beginTime = System.currentTimeMillis();

        for (int i = 0; i < executeTimes; i++) {
            latch = new CountDownLatch(TASK_THREADCOUNT);
            System.out.println("Round: "+(i+1));
            for (int j = 0; j < tasks.length; j++) {
                Thread thread = new Thread(tasks[j]);
                thread.start();
            }
          latch.await();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execute summary: Round(" +executeTimes+") ThreadCount Per Round ("
                +taskCount+") Execute Time( "+(endTime-beginTime)+"ms)" );
    }


    static class Task implements Runnable{
        List<String> tasks = new ArrayList<String>();
        Random random = new Random();
        boolean exitFlag = false;

        public void addTask(String task){
            List<String> copyTasks = new ArrayList<String>(tasks);
            copyTasks.add(task);
            tasks = copyTasks;
        }

     /*
       //未充分利用
       @Override
        public void run() {
            List<String> runTasks = tasks;
            List<String> removeTasks = new ArrayList<String>();
            for (String task:runTasks){
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(10));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                removeTasks.add(task);
            }

            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }*/


        @Override
        public void run() {
            List<String> runTasks = tasks;
            List<String> removeTasks = new ArrayList<String>();
            for (String task:runTasks){
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(10));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                removeTasks.add(task);
            }

            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            latch.countDown();
        }
    }

}
