package chap1;

/**
 * Created by hjy on 18-3-6.
 *
 Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=2M; support was removed in 8.0
 Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=4M; support was removed in 8.0
 [GC (Allocation Failure) [PSYoungGen: 32768K->576K(37888K)] 32768K->584K(123904K), 0.0314210 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
 [GC (Allocation Failure) [PSYoungGen: 33344K->576K(70656K)] 33352K->592K(156672K), 0.0281397 secs] [Times: user=0.03 sys=0.01, real=0.03 secs]
 [GC (Allocation Failure) [PSYoungGen: 66112K->608K(70656K)] 66128K->624K(156672K), 0.0719190 secs] [Times: user=0.08 sys=0.00, real=0.07 secs]
 [GC (Allocation Failure) [PSYoungGen: 66144K->576K(136192K)] 66160K->592K(222208K), 0.0856933 secs] [Times: user=0.08 sys=0.00, real=0.08 secs]
 [GC (Allocation Failure) [PSYoungGen: 131648K->576K(136192K)] 131664K->592K(222208K), 0.1510266 secs] [Times: user=0.15 sys=0.00, real=0.15 secs]
 [GC (Allocation Failure) [PSYoungGen: 131648K->592K(263168K)] 131664K->616K(349184K), 0.1905847 secs] [Times: user=0.19 sys=0.00, real=0.19 secs]
 Heap
 PSYoungGen      total 263168K, used 16297K [0x00000000d6900000, 0x00000000e6b00000, 0x0000000100000000)
 eden space 262144K, 5% used [0x00000000d6900000,0x00000000d7856638,0x00000000e6900000)
 from space 1024K, 57% used [0x00000000e6a00000,0x00000000e6a94010,0x00000000e6b00000)
 to   space 1024K, 0% used [0x00000000e6900000,0x00000000e6900000,0x00000000e6a00000)
 ParOldGen       total 86016K, used 24K [0x0000000083a00000, 0x0000000088e00000, 0x00000000d6900000)
 object space 86016K, 0% used [0x0000000083a00000,0x0000000083a06000,0x0000000088e00000)
 Metaspace       used 3073K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 333K, capacity 386K, committed 512K, reserved 1048576K

 *
 *
 */
public class PermGenGC {

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String t = String.valueOf(i).intern();  //加入常量池
        }
    }



}
