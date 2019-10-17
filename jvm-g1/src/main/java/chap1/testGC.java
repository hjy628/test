package chap1;

/**
 * Created by hjy on 18-3-6.
 */
public class testGC {

    public static void test1(){
        byte[] a = new byte[6*1024*1024];
        System.gc();
        System.out.println("First explict gc over");
    }

    public static void main(String[] args) {
        test1();
    }


}
