package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 16-1-20.
 */
public class GCPolicyDemo {
    public static void main(String[] args) throws Exception{
        System.out.println("ready to start");
        Thread.sleep(10000);
        List<GCPolicyDataObject> cacheObjects = new ArrayList<GCPolicyDataObject>();
        for (int i = 0; i < 2048; i++) {
            cacheObjects.add(new GCPolicyDataObject(100));
        }
        System.gc();
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println("Round: "+(i+1));
            for (int j = 0; j < 5; j++) {
                System.out.println("put 64M objects");
                List<GCPolicyDataObject> tmpObjects = new ArrayList<GCPolicyDataObject>();
                for (int m = 0; m < 1024; m++) {
                    tmpObjects.add(new GCPolicyDataObject(64));
                }
                tmpObjects=null;
            }
        }
        System.out.println(cacheObjects.size());
        cacheObjects=null;
    }


  static   class GCPolicyDataObject{
        byte[] bytes = null;

        GCPolicyRefObject object=null;

        public GCPolicyDataObject(int factor) {
            bytes=new byte[factor*1024];
            object = new GCPolicyRefObject();
        }
    }

  static   class GCPolicyRefObject{
        GCPolicyRefChildObject object;
        public GCPolicyRefObject() {
            object = new GCPolicyRefChildObject();
        }
    }
  static   class GCPolicyRefChildObject{
        public GCPolicyRefChildObject() {
            ;
        }
    }
}
