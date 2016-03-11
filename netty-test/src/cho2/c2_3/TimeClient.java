package cho2.c2_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by hjy on 15-9-10.
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

      //  new Thread(new ).start();
    }
}
