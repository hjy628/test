package memorydemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-21.
 * 新生代存活周期，默认值15次
 * JVM参数：  -Xms135M -Xmx135M -Xmn30M -XX:+UseSerialGC |  YGC     YGCT    FGC    FGCT     GCT
 * Execute Summary: Execute Time( 2054ms )             |  485    0.389    13    0.355    0.744
 * JVM参数：  -Xms135M -Xmx135M -Xmn30M -XX:+UseSerialGC  -XX:MaxTenuringThreshold=16 | YGC     YGCT    FGC    FGCT     GCT
 *       MaxTenuringThreshold of 16 is invalid; must be between 0 and 15  |
 *  JVM参数：  -Xms135M -Xmx135M -Xmn30M -XX:+UseSerialGC  -XX:MaxTenuringThreshold=13 | YGC     YGCT    FGC    FGCT     GCT
 *  Execute Summary: Execute Time( 2040ms )                            |                485    0.359    14    0.362    0.721
 */
public class TenuringThresholdDemo {

    public static void main(String[] args) throws Exception{

        System.out.println("wait jstat");
        Thread.sleep(10000);
        List<DataObject> objects = new ArrayList<DataObject>();
        for (int i = 0; i < 51200; i++) {
            objects.add(new TenuringThresholdDemo().new DataObject(1));
        }
        List<DataObject> tmpobjects = new ArrayList<DataObject>();
        for (int i = 0; i < 10240; i++) {
            objects.add(new TenuringThresholdDemo().new DataObject(4));
        }

        System.gc();

        Thread.sleep(10000);
        System.out.println(tmpobjects.size());
        tmpobjects=null;
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            DataObject toOldObject = new TenuringThresholdDemo().new DataObject(1024);
            for (int j = 0; j < 16; j++) {
                for (int m = 0; m < 23; m++) {
                    new TenuringThresholdDemo().new DataObject(1024);
                }
            }
            toOldObject.toString();
            toOldObject = null;
        }
        System.out.println(objects.size());
        long endTime = System.currentTimeMillis();
        System.out.println("Execute Summary: Execute Time( "+(endTime-beginTime)+"ms )");
        Thread.sleep(10000);
    }

    class DataObject{
        byte[] bytes = null;

        public DataObject(int factor) {
            bytes = new byte[factor*1024];
        }
    }
}
