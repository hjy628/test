package hash;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * @auther: hjy
 * @Date: 19-10-18 15:29
 * @Description:  消息摘要
 */

public class Digest {


    /**
     *
     * @param args  args[0] is the filename, args[1] is optionally the algorithm (SHA-1 or MD5)
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        String algname = args.length>=2?args[1]:"SHA-1";
        MessageDigest alg = MessageDigest.getInstance(algname);
        byte[] input = Files.readAllBytes(Paths.get(args[0]));
        byte[] hash = alg.digest(input);
        String d = "";
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0XFF;
            if (v <16) d+= "0";
            d += Integer.toString(v,16).toUpperCase() + "";
        }
        System.out.println(d);
    }



}
