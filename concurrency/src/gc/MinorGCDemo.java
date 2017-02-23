package gc;

/**
 * Created by hjy on 17-2-23.
 * Minor GC 触发示例
 * MemoryObject object = new MinorGCDemo().new MemoryObject(1024*1024);
 * happenMinorGC(11);
 * JVM参数：  -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails
 *
 * [GC (Allocation Failure) [PSYoungGen: 11961K->1664K(14336K)] 11961K->1664K(38912K), 0.0055518 secs] [Times: user=0.00 sys=0.02, real=0.01 secs]
 *
 * Minor GC时Survivor空间不足,对象直接进入旧生代的示例
 *  MemoryObject object = new MinorGCDemo().new MemoryObject(1024*1024);
 *  MemoryObject m2object = new MinorGCDemo().new MemoryObject(1024*1024*2);
 *      happenMinorGC(9);
 *
 *
 *      仅触发一次Minor GC的情况
 *      MemoryObject object = new MinorGCDemo().new MemoryObject(1024*1024);
 *      happenMinorGC(11);
 *      由于Serial GC 对SurvivorRatio值的使用和并行回收GC不同，因此在使用Serial GC运行上面代码时，
 *      需要调整下SurvivorRatio值，
 *      JVM参数: -XX:+UseSerialGC  -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=6
 *      [GC (Allocation Failure) [DefNew: 11715K->1508K(14336K), 0.0041527 secs] 11715K->1508K(38912K), 0.0042162 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 *
 *      并行GC
 *      JVM参数: -XX:+UseParNewGC  -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=6
 *      [GC (Allocation Failure) [ParNew: 11961K->1547K(14336K), 0.0155317 secs] 11961K->1547K(38912K), 0.0155721 secs] [Times: user=0.06 sys=0.04, real=0.02 secs]
 *
 */
public class MinorGCDemo {

    public static void main(String[] args) throws Exception{
        System.out.println("start.");
        Thread.sleep(10000);
        MemoryObject object = new MinorGCDemo().new MemoryObject(1024*1024);
     //   MemoryObject m2object = new MinorGCDemo().new MemoryObject(1024*1024*2);
        for (int i = 0; i < 2; i++) {
            happenMinorGC(11);
         //   happenMinorGC(9);
            Thread.sleep(5000);
        }

    }

    private static void happenMinorGC(int happenMinorGCIndex)throws  Exception{
        for (int i = 0; i < happenMinorGCIndex; i++) {
            if (i==happenMinorGCIndex-1){
                Thread.sleep(2000);
                System.out.println("minor gc shoud happen");
            }
            new MinorGCDemo().new MemoryObject(1024*1024);
        }
    }

    class MemoryObject{
        private byte[] bytes;

        public MemoryObject(int objectSize) {
            this.bytes=new byte[objectSize];
        }
    }
}
