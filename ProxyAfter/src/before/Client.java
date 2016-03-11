package before;

/**
 * Created by hjy on 15-12-30.
 */
public class Client {
//    静态代理
 /*   public static void main(String[] args) {
        Greeting greetingProxy = new GreetingProxy(new GreetingImpl());
        greetingProxy.sayHello("huangjiyu");
    }*/


//    动态代理
        public static void main(String[] args) {
            Greeting greeting = new JDKDynamicProxy(new GreetingImpl()).getProxy();
            greeting.sayHello("huangjiyu");
    }

}
