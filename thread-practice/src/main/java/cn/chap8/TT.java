package cn.chap8;

/**
 * Created by hjy on 17-2-8.
 */
public class TT {

    public static void main(String[] args) {

        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!!");
        System.out.println(result);



    }


}
