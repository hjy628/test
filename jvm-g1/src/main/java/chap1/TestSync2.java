package chap1;

/**
 * Created by hjy on 18-3-6.
 */
public class TestSync2 implements Runnable{
    int b = 100;

    synchronized void m1() throws InterruptedException{
        b = 1000;
     //   System.out.println("1111");
        Thread.sleep(500);
      //  System.out.println("44444");

        System.out.println("b="+b);
    }



    synchronized void m2() throws InterruptedException{
      //  System.out.println("2222");

        Thread.sleep(250);
       // System.out.println("3333");
        b = 2000;
    }

    public static void main(String[] args) throws Exception{
        TestSync2 tt = new TestSync2();
        Thread t = new Thread(tt);
        t.start();
       // System.out.println("66666");

        tt.m2();
        //System.out.println("555555");

        System.out.println("main thread b="+tt.b);


    }


    public void run() {
        try {
            m1();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
