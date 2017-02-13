package nio3;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 使用文件锁。
 * @version 1.00 2010-6-1, 17:27:37
 * @since 1.5
 * @author ZhangShixi
 */
public class UseFileLocks {

    private static final String IN_FILE = "/home/hjy/copy.sql";
    private static final int POSITION = 0;
    private static final int SIZE = 1024;

    public static void main(String[] args) throws Exception {
        // Get file channel
        RandomAccessFile raf = new RandomAccessFile(IN_FILE, "rw");
        FileChannel fc = raf.getChannel();

        // Get file lock
        System.out.println("try to get file lock ...");
        FileLock lock = fc.lock(POSITION, SIZE, false);
        System.out.println("get lock!");

        // Sleeping
        System.out.println("sleeping ...");
        Thread.sleep(6000);

        // Release lock
        System.out.println("going to release lock");
        lock.release();
        System.out.println("released lock.");

        fc.close();
        raf.close();
    }
}
