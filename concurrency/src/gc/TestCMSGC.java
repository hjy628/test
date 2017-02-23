package gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-23.
 * CMS GC concurrent mode failure失败触发的Full GC
 * JVM参数： -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
 *
 * [Full GC (Allocation Failure) [CMS[CMS-concurrent-mark: 0.001/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 (concurrent mode failure): 9697K->9697K(10240K), 0.0065156 secs] 17016K->14818K(19456K), [Metaspace: 3063K->3063K(1056768K)], 0.0065536 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 由于是并发操作的所以日志有重叠
 */
public class TestCMSGC {

    public static void main(String[] args) throws Exception{
        List<MemoryObject> objects = new ArrayList<MemoryObject>(6);
        for (int i = 0; i < 9; i++) {
            objects.add(new TestCMSGC().new MemoryObject(1024*1024));
        }
        Thread.sleep(2000);
        objects.remove(0);
        objects.remove(0);
        objects.remove(0);
        for (int i = 0; i < 20; i++) {
            objects.add(new TestCMSGC().new MemoryObject(1024*1024));
            if (i%2==0){
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
