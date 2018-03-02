package testgc;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 16-4-27.
 */
public class PSGCDirectOldDemo {
    public static void main(String[] args) throws Exception{
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
// get pid
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);

        TimeUnit.SECONDS.sleep(10);

        byte[] bytes=new byte[1024*1024*2];
        byte[] bytes2=new byte[1024*1024*2];
        byte[] bytes3=new byte[1024*1024*2];
        Thread.sleep(3000);
        System.out.println("ready to direct allocate to old");
        Thread.sleep(3000);
        byte[] bytes4 = new byte[1024*1024*4];
        Thread.sleep(3000);
    }
}
