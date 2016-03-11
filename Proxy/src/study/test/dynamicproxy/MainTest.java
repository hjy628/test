package study.test.dynamicproxy;

import study.test.staticproxy.Hello;
import study.test.staticproxy.HelloImpl;

/**
 * Created by hjy on 15-12-30.
 */
public class MainTest {
    public static void main(String[] args) {
     /*     Hello hello = new HelloImpl();

      DynamicProxy dynamicProxy = new DynamicProxy(hello);

        Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
        hello.getClass().getInterfaces(),
                dynamicProxy
        );

        helloProxy.say("hjy");*/
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
        Hello helloProxy = dynamicProxy.getProxy();
        helloProxy.say("hjy");

    }
}
