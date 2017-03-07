package chap7;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hjy on 17-3-7.
 * 使用私有的Executor,并且该Executor的生命周期受限于方法调用
 *
 * 如果某个方法需要处理一批任务，并且当所有任务都处理完成后才返回，那么可以通过一个私有的Executor来简化服务的生命周期管理，其中
 * 该Executor的生命周期是由这个方法来控制的.(在这种情况下，invokeAll和invokeAny等方法通常会起较大的作用)
 *
 * checkMail方法能在多台主机上并行地检查新邮件，它创建一个私有的Executor,并向每台主机提交一个任务。然后，当所有邮件检查任务都执行完成后，
 * 关闭Executor并等待结束
 */
public class MailToolsDemo {

    boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit)
        throws InterruptedException{

        ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (final String host :hosts) {
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (checkMail(host)){
                            hasNewMail.set(true);
                        }
                    }
                });
            }
        }finally {
            exec.shutdown();
            exec.awaitTermination(timeout, unit);
        }
        return hasNewMail.get();
    }

   static boolean checkMail(String host) {
        return true;
    }

}
