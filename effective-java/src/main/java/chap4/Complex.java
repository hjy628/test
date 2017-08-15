package chap4;

/**
 * Created by hjy on 17-7-17.
 */
public final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    //Accessors with no corressponding mutators
    public double realPart(){return re;}
    public double imaginaryPart(){return im;}

    public Complex add(Complex c){
        return new Complex(re + c.re , im + c.im);
    }

    public Complex subtract(Complex c){
        return new Complex(re - c.re , im - c.im);
    }

    public Complex multiply(Complex c){
        return new Complex(re * c.re - im *c.im,re*c.im + im * c.re );
    }

    public Complex divide(Complex c){
        double temp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im)/temp , (re * c.re - im * c.im)/temp );
    }


    @Override
    public boolean equals(Object obj) {
        if (obj==this){
            return  true;
        }
        if (!(obj instanceof Complex)){
            return false;
        }
        Complex c = (Complex) obj;

        return Double.compare(re,c.re)==0&&
                Double.compare(im,c.im)==0;
    }


    @Override
    public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 31*result + hashDouble(im);
        return result;
    }

    private int hashDouble(double val){
        long longBits = Double.doubleToLongBits(re);
        return (int)(longBits^(longBits>>>32));
    }
}
