package gc;

/**
 * Created by hjy on 17-2-23.
 * 统计得到的Minor GC晋升到旧生代的平均大小大于旧生代的剩余空间
 * JVM参数： -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:+UseParallelGC
 *
 * ready to happen one minor gc,if parallel scavenge gc,then should one full gc
 [GC (Allocation Failure) [PSYoungGen: 7479K->576K(9216K)] 7479K->6728K(19456K), 0.0035200 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
 [Full GC (Ergonomics) [PSYoungGen: 576K->0K(9216K)] [ParOldGen: 6152K->6617K(10240K)] 6728K->6617K(19456K), [Metaspace: 2984K->2984K(1056768K)], 0.0060987 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 minor gc end
 minor gc again,and should direct full gc
 [Full GC (Ergonomics) [PSYoungGen: 6382K->4105K(9216K)] [ParOldGen: 6617K->8659K(10240K)] 13000K->12764K(19456K), [Metaspace: 3066K->3066K(1056768K)], 0.0139725 secs] [Times: user=0.07 sys=0.01, real=0.02 secs]
 *
 *
 *
 *  JVM参数： -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC
 *
 *  ready to happen one minor gc,if parallel scavenge gc,then should one full gc
 [GC (Allocation Failure) [DefNew: 7479K->480K(9216K), 0.0034430 secs] 7479K->6624K(19456K), 0.0034716 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
 minor gc end
 minor gc again,and should direct full gc
 [GC (Allocation Failure) [DefNew: 6863K->6863K(9216K), 0.0000324 secs][Tenured: 6144K->8195K(10240K), 0.0052881 secs] 13007K->12764K(19456K), [Metaspace: 3066K->3066K(1056768K)], 0.0054034 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]

 */
public class TestGCDemo {
    public static void main(String[] args) throws Exception{
        byte[] bytes = new byte[1024*1024*2];
        byte[] bytes2 = new byte[1024*1024*2];
        byte[] bytes3 = new byte[1024*1024*2];
        System.out.println("ready to happen one minor gc,if parallel scavenge gc,then should one full gc");
        byte[] bytes4 = new byte[1024*1024*2];
        Thread.sleep(3000);
        System.out.println(" minor gc end");
        byte[] bytes5 = new byte[1024*1024*2];
        byte[] bytes6 = new byte[1024*1024*2];
        System.out.println("  minor gc again,and should direct full gc");
        byte[] bytes7 = new byte[1024*1024*2];
        Thread.sleep(8000);

    }
}
