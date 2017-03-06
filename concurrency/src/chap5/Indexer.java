package chap5;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hjy on 17-3-6.
 */
public class Indexer implements Runnable{
    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true)
                indexFile(queue.take());
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private static void indexFile(File file){
        System.out.println(file.getPath());
    }
}
