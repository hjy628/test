package memorydemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-21.
 * 避免新生代大小设置过小
 * 新生代设置过小时，1,minor GC次数更加频繁 2,有可能导致minor GC对象直接进入旧生代，如此占据旧生代剩余空间，触发Full GC
 * JVM参数： -Xms135M -Xmx135M -Xmn20M -XX:+UseSerialGC  执行了7次minor GC 2次Full GC
 * JVM参数： -Xms135M -Xmx135M -Xmn30M -XX:+UseSerialGC  执行了4次minor GC 1次Full GC
 * 避免新生代大小设置过大
 * 新生代设置过大时，1,旧生代变小，Full GC次数更加频繁 2, minor GC 耗时大幅度增加
 * JVM参数： -Xms135M -Xmx135M -Xmn105M -XX:+UseSerialGC  执行了4次minor GC 1次Full GC
 *
 * 大多数场景下，新生代占堆内存33%左右比较合适
 */
public class GCDemo {

    public static void main(String[] args) throws Exception{
        Thread.sleep(20000);
        System.out.println("ready to start");
        Thread.sleep(10000);
        List<GCDataObject> oldGenObjects = new ArrayList<GCDataObject>();
        for (int i = 0; i < 51200; i++) {
            oldGenObjects.add(new GCDemo().new GCDataObject(2));
        }
        System.gc();
        System.out.println(oldGenObjects.size());
        oldGenObjects=null;
        Thread.sleep(5000);
        List<GCDataObject> tmpObjects = new ArrayList<GCDataObject>();
        for (int i = 0; i < 3200; i++) {
            tmpObjects.add(new GCDemo().new GCDataObject(5));
        }
        System.out.println(tmpObjects.size());
        tmpObjects=null;
        Thread.sleep(30000);
    }


    class GCDataObject{
        byte[] bytes = null;

        RefObject object = null;

        public GCDataObject(int factor) {
            //create object in kb
            bytes = new byte[factor*1024];
            object = new RefObject();
        }
    }

    class RefObject{

        RefChildObject object;

        public RefObject() {
            this.object = new RefChildObject();
        }
    }

    class RefChildObject{

        public RefChildObject() {
            ;
        }
    }

}
