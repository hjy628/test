package chap1;

/**
 * Created by hjy on 18-3-6.
 *-XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xms40M -Xmx40M -Xmn20M
 *
 *
 *
 * [GC (System.gc()) [PSYoungGen: 12545K->1184K(18432K)] 20737K->9384K(38912K), 0.0022938 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 [Full GC (System.gc()) [PSYoungGen: 1184K->0K(18432K)] [ParOldGen: 8200K->9217K(20480K)] 9384K->9217K(38912K), [Metaspace: 2992K->2992K(1056768K)], 0.0100503 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
 Heap
 PSYoungGen      total 18432K, used 273K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
 eden space 16384K, 1% used [0x00000000fec00000,0x00000000fec44620,0x00000000ffc00000)
 from space 2048K, 0% used [0x00000000ffc00000,0x00000000ffc00000,0x00000000ffe00000)
 to   space 2048K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x0000000100000000)
 ParOldGen       total 20480K, used 9217K [0x00000000fd800000, 0x00000000fec00000, 0x00000000fec00000)
 object space 20480K, 45% used [0x00000000fd800000,0x00000000fe100788,0x00000000fec00000)
 Metaspace       used 3005K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 326K, capacity 386K, committed 512K, reserved 1048576K
 *
 */
public class TestHeapGC {

    public static void main(String[] args) {
        byte[] b1 = new byte[1024*1024/2];
        byte[] b2 = new byte[1024*1024*8];
        b2 = null;
        b2 = new byte[1024*1024*8]; //进行一次年轻带GC
        System.gc();

    }


}
