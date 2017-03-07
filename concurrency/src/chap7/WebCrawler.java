package chap7;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-3-7.
 *
 * 使用TrackingExecutorService来保存未完成的任务以备后续执行
 *
 * 网页爬虫程序的工作通常是无穷尽的，因此当爬虫程序必须关闭时，我们通常希望保存它的状态，以便稍后重新启动.
 * CrawlTask提供了一个getPage方法，该方法能找出正在处理的页面，当爬虫程序关闭时，无论是还没有开始的任务，还是那些被取消的任务，都将记录它们的URL,
 * 因此当爬虫程序重新启动时，就可以将这些URL的页面抓取任务加入到任务队列中
 *
 * 在TrackingExecutor中存在一个不可避免的竞态条件，从而产生“误报”问题：
 * 一些被认为已经取消的任务实际上已经执行完成，这个问题的原因在于，在任务执行最后一条指令以及线程池将任务记录为“结束”的两个时刻之间，线程池可能被关闭
 * 如果任务是幂等的（Idempotent,即将任务执行两次与执行一次会得到相同的结果）,那么这不会存在问题，在网页爬虫程序中就是这种情况。
 * 否则，在应用程序中必须考虑这中风险，并对“误报”问题做好准备
 */
public abstract class WebCrawler {

    private final static long TIMEOUT = 10;
    private final static TimeUnit UNTI = TimeUnit.MILLISECONDS;


    private volatile TrackingExecutor exec;

    private final Set<URL> urlsToCrawl = new HashSet<URL>();

    public synchronized void start(){
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawl) {
            submitCrawlerTask(url);
        }
        urlsToCrawl.clear();
    }


    public synchronized void stop() throws InterruptedException{
        try {
            saveUncrawled(exec.shutdownNow());
            if (exec.awaitTermination(TIMEOUT,UNTI)){
                saveUncrawled(exec.getCancelledTasks());
            }
        }finally {
            exec = null;
        }
    }

    protected abstract List<URL> processPage(URL url);

    private void saveUncrawled(List<Runnable> uncrawled){
        for (Runnable task:uncrawled) {
            urlsToCrawl.add(((CrawlTask)task).getPage());
        }
    }

    private void submitCrawlerTask(URL u){
        exec.execute(new CrawlTask(u));
    }


    private class CrawlTask implements Runnable{
        private final URL url;

        public CrawlTask(URL url) {
            this.url = url;
        }

        @Override
        public void run() {
            for (URL link: processPage(url)) {
                if (Thread.currentThread().isInterrupted()){
                    return;
                }

            }
        }
        public URL getPage(){
            return url;
        }
    }


}
