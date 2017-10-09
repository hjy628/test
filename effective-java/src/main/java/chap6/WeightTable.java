package chap6;

/**
 * Created by hjy on 17-10-9.
 * 根据某个物体在地球上的重量（以任何单位），打印出一张表格,显示出该物体在所有八颗行星上重量（用相同的单位）
 */
public class WeightTable {

    public static void main(String[] args) {
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p :
                Planet.values()) {
            System.out.printf("Weight on %s is %f%n ", p,p.surfaceGravity(mass));
        }
    }

}
