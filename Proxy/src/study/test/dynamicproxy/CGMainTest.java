package study.test.dynamicproxy;

import study.test.staticproxy.Hello;
import study.test.staticproxy.HelloImpl;

/**
 * Created by hjy on 15-12-30.
 */
public class CGMainTest {
    public static void main(String[] args) {
       /* CGLibProxy cgLibProxy = new CGLibProxy();
        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.say("huangjiyu");*/

        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("good!");
    }
}
