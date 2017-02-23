package gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-23.
 * 旧生代空间不足触发的Full GC
 * JVM参数： -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails
 *
 * [GC (Allocation Failure) [PSYoungGen: 7479K->608K(9216K)] 7479K->6760K(19456K), 0.0033491 secs] [Times: user=0.01 sys=0.01, real=0.00 secs]
 [Full GC (Ergonomics) [PSYoungGen: 608K->0K(9216K)] [ParOldGen: 6152K->6618K(10240K)] 6760K->6618K(19456K), [Metaspace: 2968K->2968K(1056768K)], 0.0127608 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
 [Full GC (System.gc()) [PSYoungGen: 4334K->1024K(9216K)] [ParOldGen: 6618K->9683K(10240K)] 10953K->10707K(19456K), [Metaspace: 2973K->2973K(1056768K)], 0.0066367 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 [Full GC (System.gc()) [PSYoungGen: 1159K->1024K(9216K)] [ParOldGen: 9683K->9683K(10240K)] 10843K->10707K(19456K), [Metaspace: 2975K->2975K(1056768K)], 0.0061861 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 [Full GC (Ergonomics) [PSYoungGen: 7377K->0K(9216K)] [ParOldGen: 9683K->4572K(10240K)] 17061K->4572K(19456K), [Metaspace: 3061K->3061K(1056768K)], 0.0053231 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
 */
public class TestFullGC {

    public static void main(String[] args) throws Exception{
        List<MemoryObject> objects = new ArrayList<MemoryObject>(6);
        for (int i = 0; i < 10; i++) {
            objects.add(new TestFullGC().new MemoryObject(1024*1024));
        }

        //让上面的对象尽可能地转入旧生代中
        System.gc();
        System.gc();
        Thread.sleep(2000);
        objects.clear();
        for (int i = 0; i < 10; i++) {
            objects.add(new TestFullGC().new MemoryObject(1024*1024));
            if (i%3==0){
                objects.remove(0);
            }
        }
        Thread.sleep(5000);
    }

    class MemoryObject{
        private byte[] bytes;

        public MemoryObject(int objectSize) {
            this.bytes = new byte[objectSize];
        }
    }
}
