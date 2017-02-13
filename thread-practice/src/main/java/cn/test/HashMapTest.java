package cn.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hjy on 17-2-13.
 */
public class HashMapTest {
    public static void main(String[] args) {

        HashMap map = new HashMap();

        map.put("key",100);
        map.put("hey",100);


        System.out.println("测试hashCode");
        System.out.println(map.get("key"));


        Map<StringOther,String> maps = new HashMap<>(16);
        StringOther so1 = new StringOther("so1");
        StringOther so2 = new StringOther("so2");
        maps.put(so1,"1");
        maps.put(so2,"2");

        System.out.println(maps);




        LinkedHashMap<String, Integer> lmap = new LinkedHashMap<String, Integer>();
        lmap.put("语文", 1);
        lmap.put("数学", 2);
        lmap.put("英语", 3);
        lmap.put("历史", 4);
        lmap.put("政治", 5);
        lmap.put("地理", 6);
        lmap.put("生物", 7);
        lmap.put("化学", 8);
        for(Map.Entry<String, Integer> entry : lmap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


}
