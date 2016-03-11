package tt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 15-9-21.
 */
public class Ltest {
    public static void main(String[] args) {
        List<String> lista = new ArrayList<String>();
        List<String> listb = new ArrayList<String>();
        for (int i=0;i<=8;i++){
            lista.add("node"+i);
        }
        for (int i=9;i<=12;i++){
            listb.add("node"+i);
        }
        listb.addAll(0,lista);
        for(String node:listb){
            System.out.println(node);
        }
    }
}
