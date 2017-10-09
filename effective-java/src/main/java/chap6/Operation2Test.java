package chap6;

/**
 * Created by hjy on 17-10-9.
 */
public class Operation2Test {
    public static void main(String[] args) {

        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        for (Operation2 op :
                Operation2.values()) {
            System.out.printf("%f %s %f = %f%n", x,op,y,op.apply(x,y));
        }
    }
}
