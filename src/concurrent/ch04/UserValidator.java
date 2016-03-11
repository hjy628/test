package concurrent.ch04;

import tt.Main;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 15-8-19.
 */
public class UserValidator {
    private String name;

    public UserValidator(String name) {
        this.name = name;
    }
    public boolean validate(String name,String password){
        Random random = new Random();
        try {
            long duration = (long)(Math.random()*10);
            System.out.printf("Validator %s: Validating a user during %d seconds\n",this.name,duration);
            TimeUnit.MILLISECONDS.sleep(duration);
        }catch (InterruptedException e){
            return false;
        }
        return  random.nextBoolean();
    }
    public String getName(){
        return name;
    }

}
