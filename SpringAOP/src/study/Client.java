package study;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by hjy on 15-12-30.
 */
public class Client {
    /*
    * 前置后置增强
    */
 /*   public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(); //创建代理工厂
        proxyFactory.setTarget(new GreetingImpl());     //射入目标类对象
*//*        proxyFactory.addAdvice(new GreetingBeforeAdvice());     //添加前置增强
        proxyFactory.addAdvice(new GreetingAfterAdvice());     //添加前后置增强*//*

        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice()); //同时添加前置增强 后置增强

        Greeting greeting = (Greeting) proxyFactory.getProxy();     //从代理工厂中获取代理
        greeting.sayHello("Jack");
    }*/

    /*
    * 环绕增强
    */

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(); //创建代理工厂
        proxyFactory.setTarget(new GreetingImpl());     //射入目标类对象
        proxyFactory.addAdvice(new GreetingAroundAdvice());
        Greeting greeting = (Greeting) proxyFactory.getProxy();     //从代理工厂中获取代理
        greeting.sayHello("Around");



    }


}
