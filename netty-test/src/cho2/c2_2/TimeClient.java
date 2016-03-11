package cho2.c2_2;

import java.io.*;
import java.net.Socket;

/**
 * Created by hjy on 15-9-9.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port =8080;
        if (args!=null&&args.length>0){
            try {
                port=Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket=new Socket("127.0.0.1",port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");
            System.out.println("send order 2 server succeed.");
            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        }catch (Exception e){
            //不需要处理
        }finally {
            if (out!=null){
                out.close();
                out = null;
            }

            if (in!=null){
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                in=null;
            }
            if (socket!=null){
                try {
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                socket=null;
            }
        }


    }
}
