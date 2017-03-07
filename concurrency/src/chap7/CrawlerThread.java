package chap7;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hjy on 17-3-7.
 * IndexingService 的生产者线程
 */
public class CrawlerThread extends Thread{

    private final BlockingQueue<File> queue;
    private final File root;
    private final File POSITION;


    public CrawlerThread(BlockingQueue queue,File root,File position){
        this.queue = queue;
        this.root = root;
        this.POSITION = position;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        }catch (InterruptedException e){/*  发生异常  */}
        finally {
            while (true){
                try {
                    queue.put(POSITION);
                    break;
                }catch (InterruptedException e1){/*  重新尝试  */}
            }
        }
    }



    private void crawl(File root) throws InterruptedException{
        //
    }
}
