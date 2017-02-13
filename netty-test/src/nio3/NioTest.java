package nio3;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by hjy on 17-2-13.
 */
public class NioTest {

    /**
     * 使用IO读取指定文件的前1024个字节的内容。
     * @param file 指定文件名称。
     * @throws java.io.IOException IO异常。
     */
    public void ioRead(String file) throws IOException {
        long statrt = System.nanoTime();


        FileInputStream in = new FileInputStream(file);
        byte[] b = new byte[1024];
        in.read(b);
        System.out.println(new String(b));

        System.out.println(System.nanoTime()-statrt);
    }

    /**
     * 使用NIO读取指定文件的前1024个字节的内容。
     * @param file 指定文件名称。
     * @throws java.io.IOException IO异常。
     */
    public void nioRead(String file) throws IOException {
        long statrt = System.nanoTime();

        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] b = buffer.array();
        System.out.println(new String(b));
        System.out.println(System.nanoTime()-statrt);
    }


    public static void main(String[] args) throws Exception{
        NioTest test = new NioTest();

        test.ioRead("/home/hjy/copy.sql");


        test.nioRead("/home/hjy/copy.sql");

    }


}
