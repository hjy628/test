package chap4;

/**
 * Created by hjy on 17-7-18.
 * 标签类示例 不推荐此种风格的类
 */
public class Figure {

    enum Shape{ RECTANGLE,CIRCLE};

    //tag field -the shape of this figure
    final Shape shape;

    double length;
    double width;

    double radius;

    public Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    public Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area(){
        switch (shape){
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI*(radius*radius);
            default:
                throw new AssertionError();
        }
    }


}
