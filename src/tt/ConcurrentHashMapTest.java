package tt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hjy on 16-3-31.
 */
public class ConcurrentHashMapTest {
//    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
    public static void main(String[] args) {
       /* new Thread("Thread1"){
            @Override
            public void run() {
                map.put(3, 33);
            }
        }.start();

        new Thread("Thread2"){
            @Override
            public void run() {
                map.put(4, 44);
            }
        }.start();

        new Thread("Thread3"){
            @Override
            public void run() {
                map.put(7, 77);
            }
        }.start();


        System.out.println(map);*/

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("语文", 1);
        map.put("数学", 2);
        map.put("英语", 3);
        map.put("历史", 4);
        map.put("政治", 5);
        map.put("地理", 6);
        map.put("生物", 7);
        map.put("化学", 8);
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
