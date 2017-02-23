package gc;

/**
 * Created by hjy on 17-2-23.
 * Parallel Scavenge 并行回收gc
 * 不是根据-XX:PretenureSizeThreshold来决定对象是否在旧生代上直接分配，而是当需要给对象分配内存时，eden space空间不够
 * 的情况下，如果此对象的大小大于等于eden space一半的大小，就直接在旧生代上分配
 * 可用-XX:ParallelGCThreads=4来强制指定线程数
 *
 * JVM参数: -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseParallelGC
 */
public class PSGDirectOldDemo {

    public static void main(String[] args) throws Exception{
        Thread.sleep(10000);
        byte[] bytes = new byte[1024*1024*2];
        byte[] bytes2 = new byte[1024*1024*2];
   //     byte[] bytes3 = new byte[1024*1024*2];
        System.out.println("ready to direct allocate to old");
        Thread.sleep(3000);
        byte[] bytes4 = new byte[1024*1024*4];
        Thread.sleep(5000);

    }


}
