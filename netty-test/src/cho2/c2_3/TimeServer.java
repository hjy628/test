package cho2.c2_3;

import java.io.IOException;

/**
 * Created by hjy on 15-9-9.
 */
public class TimeServer {
    public static void main(String[] args) throws IOException{
        int port = 8080;
        if (args!=null&&args.length>0){
            try {
                port=Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }




    }
}
