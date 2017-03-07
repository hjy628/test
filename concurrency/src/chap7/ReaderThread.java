package chap7;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by hjy on 17-3-7.
 * 通过改写interrupt方法将非标准的取消操作封装在Thread中
 * 如何封装非标准的取消操作，管理了一个套接字链接，它采用同步方式从该套接字中读取数据，并将接收到的数据传递给processBuffer
 * 为了结束某个用户的连接或者关闭服务器，改写了interrupt方法，使其既能处理标准的中断，也能关闭底层的套接字
 */
public class ReaderThread extends Thread{
    private final Socket socket;
    private final InputStream in;
    private final int BUFSZ;

    public ReaderThread(Socket socket)throws IOException{
        this.socket = socket;
        this.in = socket.getInputStream();
        this.BUFSZ = 1024;
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        }catch (IOException ignored){}
        finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true){
                int count = in.read(buf);
                if (count<0){
                    break;
                }else if (count>0){
                    //processBuffer(buf,count);
                }
            }
        }catch (IOException e){
            //允许线程退出
        }
    }
}
