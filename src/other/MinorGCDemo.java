package other;

import java.util.Map;

/**
 * Created by hjy on 16-3-17.
 */
public class MinorGCDemo {
    public static void main(String[] args) throws Exception{
        System.out.println(System.getenv());
        Map map=System.getenv();




        MemoryObject object = new MemoryObject(1024*1024);
        for (int i = 0; i < 2; i++) {
            happenMinorGC(11);
            Thread.sleep(3000);
        }
    }


    private static void happenMinorGC(int happenMinorGCIndex)throws Exception{
        for (int i = 0; i < happenMinorGCIndex; i++) {
            if (i==happenMinorGCIndex-1){
                Thread.sleep(3000);
                System.out.println("minor gc should happen ");
            }
            new MemoryObject(1024*1024);
        }
    }


   static class MemoryObject{
        private byte[] bytes;

        public MemoryObject(int objectSize) {
            this.bytes = new byte[objectSize];
        }
    }
}
