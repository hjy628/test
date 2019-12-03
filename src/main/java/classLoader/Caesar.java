package classLoader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @auther: hjy
 * @Date: 19-10-18 10:08
 * @Description:  源码加密类
 */

public class Caesar {
    public static void main(String[] args) throws Exception{
        if (args.length != 3) {
            System.out.println("USAGE: java classLoader.Caesat in out key");
            return;
        }

        try (FileInputStream in = new FileInputStream(args[0]);
             FileOutputStream out = new FileOutputStream(args[1])){
            int key = Integer.parseInt(args[2]);
            int ch;
            while ((ch = in.read())!=-1){
                byte c = (byte) (ch+ key);
                out.write(c);
            }
        }

    }


}
