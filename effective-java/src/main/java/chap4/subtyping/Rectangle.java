package chap4.subtyping;

/**
 * Created by hjy on 17-7-18.
 */
public class Rectangle extends Figure{
    final double length;
    final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double area() {
        return length*width;
    }
}
