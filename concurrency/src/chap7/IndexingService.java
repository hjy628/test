package chap7;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hjy on 17-3-7.
 *
 * 通过毒丸对象来关闭服务
 */
public class IndexingService {
    private static final File POSITION = new File("");
    private final BlockingQueue<File> queue = new ArrayBlockingQueue<File>(10);
    private final FileFilter fileFilter;
    private final File root = new File("/home");


    public IndexingService( FileFilter fileFilter) {
        this.fileFilter = fileFilter;
    }

    private final IndexerThread consumer = new IndexerThread(queue,POSITION);
    private final CrawlerThread producer = new CrawlerThread(queue,root,POSITION);

    public void start(){
        producer.start();
        consumer.start();
    }

    public void stop(){
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException{
        consumer.join();
    }


}
