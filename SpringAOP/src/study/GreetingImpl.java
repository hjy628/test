package study;

/**
 * Created by hjy on 15-12-30.
 */
public class GreetingImpl implements Greeting{
    @Override
    public void sayHello(String name) {
        System.out.println("Hello!" + name);
    }



}
