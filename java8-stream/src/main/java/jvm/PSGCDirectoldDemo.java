package jvm;

/**
 * Created by hjy on 17-2-20. UseParallelGC测试
 * VM参数: -Xms20M -Xmn10M -Xmx20M -XX:SurvivorRatio=8 -XX:+UseParallelGC
 */
public class PSGCDirectoldDemo {

    public static void main(String[] args) throws Exception{
        byte[] bytes = new byte[1024*1024*2];
        byte[] bytes2 = new byte[1024*1024*2];
        byte[] bytes3 = new byte[1024*1024*2];
        System.out.println("ready to direct allocate to old");
        Thread.sleep(12000);
        byte[] bytes4 = new byte[1024*1024*4];
        Thread.sleep(6000);
    }


}
