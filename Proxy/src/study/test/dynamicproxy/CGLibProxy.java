package study.test.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hjy on 15-12-30.
 */
public class CGLibProxy implements MethodInterceptor{

    private static CGLibProxy instance = new CGLibProxy();

    private CGLibProxy(){

    }

    public static CGLibProxy getInstance(){
        return instance;
    }

    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before();
        Object result = proxy.invokeSuper(obj, args);
        after();
        return result;
    }


    private void before(){
        System.out.println("Before");
    }
    private void after(){
        System.out.println("After");
    }
}
