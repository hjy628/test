package chap6;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by hjy on 17-10-9.
 */
public class OperationInterTest {

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(ExtendedOperation.class,x,y);
        test2(Arrays.asList(ExtendedOperation.values()),x,y);
    }


    private static <T extends Enum<T> & OperationInter> void test(Class<T> opSet,double x,double y){
        for (OperationInter op :opSet.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n",x,op,y,op.apply(x, y));
        }
    }

    private static  void test2(Collection<? extends OperationInter> opSet, double x, double y){
        for (OperationInter op :opSet) {
            System.out.printf("%f %s %f = %f%n",x,op,y,op.apply(x, y));
        }
    }


}
