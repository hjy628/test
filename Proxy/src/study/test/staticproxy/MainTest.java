package study.test.staticproxy;

/**
 * Created by hjy on 15-12-30.
 */
public class MainTest {
    public static void main(String[] args) {
        Hello helloProxy = new HelloProxy();
        helloProxy.say("hjy");
    }
}
