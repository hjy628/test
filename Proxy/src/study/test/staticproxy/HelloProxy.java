package study.test.staticproxy;

/**
 * Created by hjy on 15-12-30.
 */
public class HelloProxy implements Hello{
    private Hello hello;

    public HelloProxy() {
        hello=new HelloImpl();
    }

    @Override
    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    private void before(){
        System.out.println("Before");
    }
    private void after(){
        System.out.println("After");
    }


}
