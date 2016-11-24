package netty.chap1.pio;

import netty.chap1.bio.TimeServerHandler;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hjy on 16-2-24.
 */
public class TimeServer {
    public static void main(String[] args) throws Exception{
        int port = 8090;
        if (args!=null&&args.length>0){
            try {
                port=Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }

        ServerSocket server = null;
        try {
            server=new ServerSocket(port);
            System.out.println("The Time server is start in port:"+port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(
                    50,10000);    //创建I/O任务线程池
            while (true){
                socket=server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        }finally {
            if (server!=null){
                System.out.println("The time server close");
                server.close();
                server=null;
            }
        }



    }
}
