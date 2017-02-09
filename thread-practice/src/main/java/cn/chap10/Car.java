package cn.chap10;

import java.util.Optional;

/**
 * Created by hjy on 17-2-9.
 */
public class Car {
 /*   private Insurance insurance;

    public Insurance getInsurance() {
        return insurance;
    }*/

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
