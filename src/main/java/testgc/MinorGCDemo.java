package testgc;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 16-4-27.
 */
public class MinorGCDemo {
    public static void main(String[] args) throws Exception{
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
// get pid
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);

        TimeUnit.SECONDS.sleep(4);

        MemoryObject object = new  MemoryObject(1024*1024);
        for (int i = 0; i < 2; i++) {

            happenMinorGC(11);
            Thread.sleep(2000);
        }
    }


    private static void happenMinorGC(int happenMinorGCIndex)throws Exception{
        for (int i = 0; i < happenMinorGCIndex; i++) {
            if (i==happenMinorGCIndex-1){
                Thread.sleep(2000);
                System.out.println("minor gc shoule happen");
            }
            new MemoryObject(1024*1024);
        }
    }




}
