package jvm;

/**
 * Created by hjy on 17-2-20.
 * VM参数:-Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails
 */
public class MinorGCDemo {

    public static void main(String[] args) throws Exception{
        MemoryObject object = new MinorGCDemo().new MemoryObject(1024*1024);
        for (int i = 0; i < 2; i++) {
            happenMinorGC(11);
            Thread.sleep(2000);
        }


    }

    private static void happenMinorGC(int happenMinorGCIndex) throws Exception{
        for (int i = 0; i < happenMinorGCIndex; i++) {
            if (i==happenMinorGCIndex-1){
                Thread.sleep(2000);
                System.out.println("minor gc shoud happen");
            }
            new MinorGCDemo().new MemoryObject(1024*1024);
        }
    }

    class MemoryObject{
        private byte[] bytes;
        public MemoryObject(int objectSize) {
            this.bytes = new byte[objectSize];
        }
    }

}
