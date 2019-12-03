package aes;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

/**
 * @auther: hjy
 * @Date: 19-10-18 16:44
 * @Description:
 */

public class Util {

    /**
     * Uses a cipher to transform the bytes in an input stream and sends the transformed bytes to
     * an output stream
     * @param in the input stream
     * @param out the output stream
     * @param cipher the cipher that transforms the bytes
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, GeneralSecurityException{
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];
        int inLength = 0;
        ;
        boolean more = true;
        while (more)
        {
            inLength = in.read(inBytes);
            if (inLength == blockSize)
            {
                int outLength = cipher.update(inBytes,0,blockSize,outBytes);
                out.write(outBytes,0,outLength);
            }
            else more = false;
        }
        if (inLength > 0) outBytes = cipher.doFinal(inBytes,0,inLength);
        else outBytes = cipher.doFinal();
        out.write(outBytes);



    }



}
