package chap6;

/**
 * Created by hjy on 17-10-9.
 * 特定于常量的方法实现
 */
public enum Operation2 {
    PLUS("+"){ double apply(double x,double y){return x + y;}},
    MINUS("-"){ double apply(double x,double y){return x - y;}},
    TIMES("*"){ double apply(double x,double y){return x * y;}},
    DIVIDE("/"){ double apply(double x,double y){return x / y;}};

    private final String symbol;

    Operation2(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return  symbol ;
    }

    abstract double apply(double x, double y);
}
