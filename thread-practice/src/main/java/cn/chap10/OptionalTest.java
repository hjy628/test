package cn.chap10;

import java.util.Optional;

/**
 * Created by hjy on 17-2-9.
 */
public class OptionalTest {

    public void empty(){
        Optional<Car> optCar = Optional.empty();
    }

    private void car(Car car){
        Optional<Car> optional = Optional.of(car);
    }

    private void nullCar(Car car){
        Optional<Car> optional = Optional.ofNullable(car);
    }

    private void getInsName(Insurance insurance){
        Optional<Insurance> optional = Optional.ofNullable(insurance);
        Optional<String> name = optional.map(Insurance::getName);
    }
    private void getPersonInsName(Person person){
        Optional<Person> optional = Optional.ofNullable(person);
        String name = optional.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
    }

}
