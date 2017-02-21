package memorydemo;

import java.nio.ByteBuffer;

/**
 * Created by hjy on 17-2-21.
 * 基于Direct ByteBuffer实现对物理内存的直接操作
 * VM惨素：-Xms140M-Xmx140M
 * top java进程内存
 * jstat -gcutil pid 5 10  JVM堆内存
 */
public class DirectByteBufferDemo {

    public static void main(String[] args) throws Exception{
        System.out.println("start");
        Thread.sleep(50000);
        System.out.println("read to create bytes,so JVM heap will be used");
        byte[] bytes = new byte[128*1000*1000];
        bytes[0]=1;
        bytes[1]=2;
        Thread.sleep(30000);
        System.out.println("read to allocate & put direct bytebuffer,no JVM heap should be used");
        ByteBuffer buffer = ByteBuffer.allocate(128*1024*1024);
        buffer.put(bytes);
        buffer.flip();
        Thread.sleep(30000);
        System.out.println("ready to gc,so JVM heap will be freed");
        bytes=null;
        System.gc();
        Thread.sleep(30000);
        System.out.println("read to get bytes,then JVM heap will be used");
        byte[] resultbytes = new byte[128*1000*1000];
        buffer.get(resultbytes);
        System.out.println("resultbytes[1] is: "+resultbytes[1]);
        Thread.sleep(30000);
        System.out.println("ready to gc all");
        buffer=null;
        resultbytes=null;
        System.gc();
        Thread.sleep(50000);
    }


}
