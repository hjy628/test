package concurrent.ch05;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by hjy on 16-2-25.
 */
public class Test {
    public static void main(String[] args) throws Exception{
        final HashMap<String,String> map = new HashMap<String, String>(2);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(),"");
                        }
                    },"ftf"+i).start();
                }
            }
        },"ftf");
        t.start();
        t.join();
    }
}
