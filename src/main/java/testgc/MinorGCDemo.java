package testgc;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 16-4-27.
 * -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails
 */
public class MinorGCDemo {
    public static void main(String[] args) throws Exception{
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
// get pid
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);

        TimeUnit.SECONDS.sleep(14);

       /*
       -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails
       MemoryObject object = new  MemoryObject(1024*1024);
        for (int i = 0; i < 2; i++) {

            happenMinorGC(11);
            Thread.sleep(2000);
              for (int i = 0; i < 2; i++) {

            happenMinorGC(11);
            Thread.sleep(2000);
        }
        }*/
/*
        -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails
        MemoryObject object = new  MemoryObject(1024*1024);
        MemoryObject object2 = new  MemoryObject(2*1024*1024);
        happenMinorGC(9);
        Thread.sleep(2000);*/

        //-XX:+UseSerialGC -XX:SurvivorRatio=6 -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails
        //-XX:+UseParNewGC -XX:SurvivorRatio=6 -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails
        MemoryObject object = new  MemoryObject(1024*1024);
        happenMinorGC(11);
        Thread.sleep(2000);

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
