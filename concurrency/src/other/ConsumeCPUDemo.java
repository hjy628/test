package other;

import java.util.ArrayList;

/**
 * Created by hjy on 17-2-15.
 */
public class ConsumeCPUDemo {

    public static void main(String[] args) throws Exception{
        ConsumeCPUDemo demo = new ConsumeCPUDemo();
        demo.runTest();
    }


    private void runTest() throws Exception{
        int count = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < count; i++) {
            new Thread(new ConsumeCPUTask()).start();
        }

        for (int i = 0; i < 200; i++) {
            new Thread(new NotConsumeCPUTask()).start();
        }
    }

    class ConsumeCPUTask implements Runnable{

        @Override
        public void run() {
            String str = "sdfeferfwefwetwrefrefwefwefwenfjweoifjweio"+
                    "asdogfiaetfgoerug0rbv89g09324tjgioopju890u20893r9302rie09wfjdsvjiug"+
                    "sdfhoy23q80ur902r0934jegvmfdbv;sdkvelwopfkoperwjgifregirejvgfpwekrofkwefkcxlvmdf;;"+
                    "ASQWQE2345325476hjigufpobnvmc;mkscvk[dsifcpdsrgjivoerjgioergiergfrpoweiforejgboi;"+
                    "cvbjldfiogweuf09wer0f9342ut50gj+_+(^&#IBFUIH(DFY*S^&%$^!@$$*~!)*()@#HRFD";

            float i = 0.002f;
            float j = 232.13243f;
            while (true){
                j=i*j;
                str.indexOf("#");
                ArrayList<String> list = new ArrayList<String>();
                for (int k = 0; k < 10000; k++) {
                    list.add(str+String.valueOf(k));
                }
                list.contains("iii");
                try {
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }

    class NotConsumeCPUTask implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(10000000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
