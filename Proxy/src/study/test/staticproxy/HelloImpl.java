package study.test.staticproxy;

/**
 * Created by hjy on 15-12-30.
 */
public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello! "+name);
    }
}
