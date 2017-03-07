package chap7;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hjy on 17-3-7.
 * IndexingService 的消费者线程
 */
public class IndexerThread extends Thread{

    private final BlockingQueue<File> queue;
    private final File POSITION;

    public IndexerThread(BlockingQueue queue,File position){
        this.queue = queue;
        this.POSITION = position;
    }

    @Override
    public void run() {
        try {
            while (true){
                File file = queue.take();
                if (file == POSITION)
                    break;
                else
                 //   indexFile(file);
                file.canRead();
            }
        }catch (InterruptedException consumed){ }
    }
}
