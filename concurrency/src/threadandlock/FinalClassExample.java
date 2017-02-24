package threadandlock;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by hjy on 17-2-24.
 */
public class FinalClassExample {

    private final int id;

    private final String name;

    private final HashMap testMap;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * 可变对象的访问方法
     */
    public HashMap getTestMap() {
        //return testMap;
        return (HashMap) testMap.clone();
    }

    /**
     * 实现深拷贝(deep copy)的构造器
     * @param i
     * @param n
     * @param hm
     */

    public FinalClassExample(int i, String n, HashMap hm){
        System.out.println("Performing Deep Copy for Object initialization");
        this.id=i;
        this.name=n;
        HashMap tempMap=new HashMap();
        String key;
        Iterator it = hm.keySet().iterator();
        while(it.hasNext()){
            key= (String) it.next();
            tempMap.put(key, hm.get(key));
        }
        this.testMap=tempMap;
    }

    /**
     * 实现浅拷贝(shallow copy)的构造器
     * @param i
     * @param n
     * @param hm
     */
    /**
     public FinalClassExample(int i, String n, HashMap hm){
     System.out.println("Performing Shallow Copy for Object initialization");
     this.id=i;
     this.name=n;
     this.testMap=hm;
     }
     */

    /**
     * 测试浅拷贝的结果
     * 为了创建不可变类，要使用深拷贝
     * @param args
     */
    public static void main(String[] args) {
        HashMap h1 = new HashMap();
        h1.put("1", "first");
        h1.put("2", "second");

        String s = "original";

        int i=10;

        FinalClassExample ce = new FinalClassExample(i,s,h1);

        //Lets see whether its copy by field or reference
        System.out.println(s==ce.getName());
        System.out.println(h1 == ce.getTestMap());
        //print the ce values
        System.out.println("ce id:"+ce.getId());
        System.out.println("ce name:"+ce.getName());
        System.out.println("ce testMap:"+ce.getTestMap());
        //change the local variable values
        i=20;
        s="modified";
        h1.put("3", "third");
        //print the values again
        System.out.println("ce id after local variable change:"+ce.getId());
        System.out.println("ce name after local variable change:"+ce.getName());
        System.out.println("ce testMap after local variable change:"+ce.getTestMap());

        HashMap hmTest = ce.getTestMap();
        hmTest.put("4", "new");

        System.out.println("ce testMap after changing variable from accessor methods:"+ce.getTestMap());

    }


}
