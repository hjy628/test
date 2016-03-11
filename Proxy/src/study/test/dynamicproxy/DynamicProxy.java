package study.test.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hjy on 15-12-30.
 */
public class DynamicProxy implements InvocationHandler{
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       /* Throwable {*/
            before();
            Object result = method.invoke(target,args);
            after();
            return result;
       /* }*/
    }


    private void before(){
        System.out.println("Before");
    }
    private void after(){
        System.out.println("After");
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

}
