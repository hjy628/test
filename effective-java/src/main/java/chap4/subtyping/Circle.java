package chap4.subtyping;

import java.io.File;

/**
 * Created by hjy on 17-7-18.
 */
public class Circle extends Figure{

    final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return Math.PI*(radius*radius);
    }
}
