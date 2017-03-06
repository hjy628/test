package chap5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hjy on 17-3-6.
 */
public class FileCrawler implements Runnable{

    private final BlockingQueue<File> filesQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> filesQueue, FileFilter fileFilter, File root) {
        this.filesQueue = filesQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root)throws InterruptedException{
        File[] entries = root.listFiles(fileFilter);
        if (entries!=null){
            for (File entry :
                    entries) {
                if (entry.isDirectory()){
                    crawl(entry);
                }else if (entry.canRead()){
                    filesQueue.put(entry);
                }
            }
        }
    }
}
