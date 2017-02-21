package memorydemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-21.
 * 一
 * 避免新生代大小设置过小
 * 新生代设置过小时，1,minor GC次数更加频繁 2,有可能导致minor GC对象直接进入旧生代，如此占据旧生代剩余空间，触发Full GC
 * JVM参数： -Xms135M -Xmx135M -Xmn20M -XX:+UseSerialGC  执行了7次minor GC 2次Full GC
 * JVM参数： -Xms135M -Xmx135M -Xmn30M -XX:+UseSerialGC  执行了4次minor GC 1次Full GC
 *
 * 二
 * 避免新生代大小设置过大
 * 新生代设置过大时，1,旧生代变小，Full GC次数更加频繁 2, minor GC 耗时大幅度增加
 * JVM参数： -Xms135M -Xmx135M -Xmn105M -XX:+UseSerialGC  执行了4次minor GC 1次Full GC
 *
 * 大多数场景下，新生代占堆内存33%左右比较合适
 *
 * 三
 * 避免Survivor区过小或过大
 * JVM参数： -Xms135M -Xmx135M -Xmn20M -XX:SurvivorRatio=10 -XX:+UseSerialGC  执行了6次minor GC 1次Full GC
 * 没有调大新生代空间的情况下，同样避免了第二次Full GC的发生， tmpObjects在创建过程中需要大概16M空间，新生代大小设置20M 默认Eden大小16M,两个Survivor分别为2M
 * 当tmpObjects创建时会填满Eden space 从而触发minor GC ，而此时这16M的对象都是有引用的对象，minor GC 时只能将其放入Survivor区，但Survivor只有2M
 * 只有将14M的对象转入旧生代中，而旧生代大小115M 之前已用101M左右的空间，当这14M加入时，旧生代被占满，于是Full GC被触发，在加入-XX:SurvivorRatio=10之后
 * Eden区大小调整为16.7M 因此当tmpObjects创建完毕时,不足以触发minor GC 从而避免了Full GC
 *
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
