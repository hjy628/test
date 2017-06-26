package optional;

/**
 * Created by hjy on 17-6-22.
 */
public class Person {
    private Car car;

    public Car getCar() {
        return car;
    }

    public static void main(String[] args) {
        System.out.println(new Person().getCarInsuranceName(new Person()));
    }

/*    public String getCarInsuranceName(Person person){
        return person.getCar().getInsurance().getName();
    }*/

    public String getCarInsuranceName(Person person){
        if (person!=null){
            Car car = person.getCar();
            if (car!=null){
                Insurance insurance = car.getInsurance();
                if (insurance!=null){
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }
}
