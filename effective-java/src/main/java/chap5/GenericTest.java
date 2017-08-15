package chap5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-7-18.
 */
public class GenericTest {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings,new Integer(42));
        String s= strings.get(0);

    }


    private static void unsafeAdd(List list,Object o){
            list.add(o);
    }



}
