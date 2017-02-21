package memorydemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-2-21.
 * GC策略的调优
 * JVM参数： -Xms680M -Xmx680M -Xmn80M -XX:+UseConcMarkSweepGC  -XX:+PrintGCApplicationStoppedTime
 * -XX:+UseCMSCompactAtFullCollection -XX:CMSMaxAbortablePrecleanTime=5  YGC     YGCT    FGC    FGCT     GCT |   53    3.649    23    0.087    3.737
 *
 *JVM参数： -Xms680M -Xmx680M -Xmn80M -XX:+UseParallelGC  -XX:+PrintGCApplicationStoppedTime
 *   YGC     YGCT    FGC    FGCT     GCT |  120    2.328     9    0.119    2.448
 *
 */
public class GCPolicyDemo {

    public static void main(String[] args) throws Exception{
        System.out.println("ready to start");
        TimeUnit.SECONDS.sleep(10);
        List<GCPolicyDataObject> cacheObjects = new ArrayList<GCPolicyDataObject>();
        for (int i = 0; i < 2048; i++) {
            cacheObjects.add(new GCPolicyDemo().new GCPolicyDataObject(100));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < 10; i++) {
            System.out.println("Round: "+(i+1));
            for (int j = 0; j < 5; j++) {
                System.out.println("put 64M objects");
                List<GCPolicyDataObject> tmpObjects = new ArrayList<GCPolicyDataObject>();
                for (int m = 0; m < 1024; m++) {
                    tmpObjects.add(new GCPolicyDemo().new GCPolicyDataObject(64));
                }
                tmpObjects = null;
            }
        }
        System.out.println(cacheObjects.size());
        cacheObjects = null;
        TimeUnit.SECONDS.sleep(10);
    }

    class GCPolicyDataObject{

        byte[] bytes = null;

        GCPolicyRefObject object = null;

        public GCPolicyDataObject(int factor) {
            bytes = new byte[factor*1024];
            object = new GCPolicyRefObject();
        }
    }


    class GCPolicyRefObject{
        GCPolicyRefChildObject object;

        public GCPolicyRefObject() {
            object = new GCPolicyRefChildObject();
        }
    }


    class GCPolicyRefChildObject{
        public GCPolicyRefChildObject() {
            ;
        }
    }
}
