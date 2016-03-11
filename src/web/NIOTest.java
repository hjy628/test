package web;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hjy on 16-2-26.
 */
public class NIOTest {
    public static void main(String[] args) {

    }

    public void selector()throws IOException{
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            Set selectedKeys = selector.selectedKeys();//取得所有的key集合
            Iterator it = selectedKeys.iterator();
            while (it.hasNext()){
                SelectionKey key=(SelectionKey)it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT){
                    ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
                    SocketChannel sc = ssChannel.accept();//接收到服务端的请求
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                    it.remove();
                }else if ((key.readyOps()&SelectionKey.OP_READ)==SelectionKey.OP_READ){
                    SocketChannel sc = (SocketChannel)key.channel();
                    while (true){
                        buffer.clear();
                        int n = sc.read(buffer);//读取数据
                        if (n<=0){break;}
                    }
                    buffer.flip();
                }
                it.remove();
            }
        }
    }

}
