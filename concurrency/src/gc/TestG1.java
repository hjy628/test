package gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-23.
 * G1垃圾收集
 * JVM参数：  -Xms40M -Xmx40M -Xmn20M -verbose:gc -XX:+PrintGCDetails -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC
 * -XX:MaxGCPauseMillis=5 -XX:GCPuaseIntervalMillis=1000
 *
 *
 * [GC pause (G1 Humongous Allocation) (young) (initial-mark), 0.0057794 secs]
 [Parallel Time: 1.3 ms, GC Workers: 8]
 [GC Worker Start (ms): Min: 145.3, Avg: 145.6, Max: 145.8, Diff: 0.5]
 [Ext Root Scanning (ms): Min: 0.1, Avg: 0.6, Max: 1.2, Diff: 1.1, Sum: 4.5]
 [Update RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 [Processed Buffers: Min: 0, Avg: 0.0, Max: 0, Diff: 0, Sum: 0]
 [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 [Object Copy (ms): Min: 0.0, Avg: 0.2, Max: 0.4, Diff: 0.4, Sum: 1.7]
 [Termination (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 1.1]
 [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
 [GC Worker Total (ms): Min: 0.7, Avg: 0.9, Max: 1.3, Diff: 0.5, Sum: 7.5]
 [GC Worker End (ms): Min: 146.5, Avg: 146.5, Max: 146.5, Diff: 0.0]
 [Code Root Fixup: 0.0 ms]
 [Code Root Purge: 0.0 ms]
 [Clear CT: 0.1 ms]
 [Other: 4.4 ms]
 [Choose CSet: 0.0 ms]
 [Ref Proc: 4.2 ms]
 [Ref Enq: 0.0 ms]
 [Redirty Cards: 0.1 ms]
 [Humongous Reclaim: 0.0 ms]
 [Free CSet: 0.0 ms]
 [Eden: 2048.0K(20.0M)->0.0B(19.0M) Survivors: 0.0B->1024.0K Heap: 10.8M(40.0M)->6704.2K(40.0M)]
 [Times: user=0.02 sys=0.00, real=0.01 secs]
 [GC concurrent-root-region-scan-start]
 [GC concurrent-root-region-scan-end, 0.0013000 secs]
 [GC concurrent-mark-start]
 [GC concurrent-mark-end, 0.0000397 secs]
 [GC remark [Finalize Marking, 0.0004282 secs] [GC ref-proc, 0.0001167 secs] [Unloading, 0.0006531 secs], 0.0013206 secs]
 [Times: user=0.01 sys=0.00, real=0.00 secs]
 [GC cleanup 18M->18M(40M), 0.0012665 secs]
 [Times: user=0.00 sys=0.00, real=0.00 secs]
 */
public class TestG1 {

    public static void main(String[] args) throws Exception{
        List<MemoryObject> objects = new ArrayList<MemoryObject>();
        for (int i = 0; i < 20; i++) {
            objects.add(new TestG1().new MemoryObject(1024*1024));
            if (i%3==0){
                objects.remove(0);
            }
        }
        Thread.sleep(2000);
    }

    class MemoryObject{
        private byte[] bytes;

        public MemoryObject(int objectSize) {
            this.bytes = new byte[objectSize];
        }
    }
}
