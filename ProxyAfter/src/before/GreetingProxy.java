package before;

/**
 * Created by hjy on 15-12-30.
 */
public class GreetingProxy implements Greeting{
    private GreetingImpl greetingImpl;

    public GreetingProxy(GreetingImpl greetingImpl) {
        this.greetingImpl = greetingImpl;
    }

    @Override
    public void sayHello(String name) {
        before();
        greetingImpl.sayHello(name);
        after();
    }


    private void before(){
        System.out.println("Before");
    }
    private void after(){
        System.out.println("After");
    }

}
