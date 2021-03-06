package other;

import java.lang.reflect.Method;

/**
 * Created by hjy on 16-3-17.
 */
public class Demo {
    private static final int WARMUP_COUNT=10700;
    private ForReflection testClass = new ForReflection();
    private static Method method=null;

    public static void main(String[] args) throws Exception{
        method= ForReflection.class.getMethod("execute",new Class<?>[]{String.class});
        Demo demo = new Demo();
        //保证反射能生成字节码及相关的测试代码能够被jit编译
        for (int i = 0; i < 20; i++) {
            demo.testDirectCall();
            demo.testCacheMethodCall();
            demo.testNoCacheMethodCall();
        }
        long beginTime = System.currentTimeMillis();
        demo.testDirectCall();
        long endTime = System.currentTimeMillis();
        System.out.println("直接调用消耗的时间为：　"+(endTime-beginTime)+"毫秒");
        beginTime=System.currentTimeMillis();
        demo.testNoCacheMethodCall();
        endTime=System.currentTimeMillis();
        System.out.println("不缓存Method,反射调用消耗的时间为：　"+(endTime-beginTime)+"毫秒");
        demo.testNoCacheMethodCall();
        demo.testCacheMethodCall();
        endTime=System.currentTimeMillis();
        System.out.println("缓存Method,反射调用消耗的时间为：　"+(endTime-beginTime)+"毫秒");





    }

    public void testDirectCall(){
        for (int i = 0; i < WARMUP_COUNT; i++) {
            testClass.execute("hello");
        }
    }

    public void testCacheMethodCall()throws Exception{
        for (int i = 0; i < WARMUP_COUNT; i++) {
            method.invoke(testClass,new Object[]{"hello"});
        }
    }


    public void testNoCacheMethodCall()throws Exception{
        for (int i = 0; i < WARMUP_COUNT; i++) {
            Method testMethod = ForReflection.class.getMethod("execute",new Class<?>[]{ String.class});
            testMethod.invoke(testClass,new Object[]{"hello"});
        }
    }





}
