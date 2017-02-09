package cn.chap10;

import java.util.Optional;

/**
 * Created by hjy on 17-2-9.
 */
public class Person {
/*    private Car car;

    public Car getCar() {
        return car;
    }*/

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
