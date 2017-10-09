package chap6;

/**
 * Created by hjy on 17-10-9.
 */
public enum BasicOperation implements OperationInter {
    PLUS("+"){ public double apply(double x,double y){return x + y;}},
    MINUS("-"){ public double apply(double x,double y){return x - y;}},
    TIMES("*"){ public double apply(double x,double y){return x * y;}},
    DIVIDE("/"){ public double apply(double x,double y){return x / y;}};

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return  symbol ;
    }

}
