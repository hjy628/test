package study;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by hjy on 15-12-31.
 */
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        return super.invoke(mi);
    }

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry!"+name);
    }
}
