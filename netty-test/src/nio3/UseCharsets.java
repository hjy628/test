package nio3;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 使用字符集。
 *
 * 它从一个文件中读取一些文本，并将该文本写入另一个文件。
 * 但是它把该数据当作文本数据，并使用CharBuffer来将该数句读入一个CharsetDecoder中。
 * 同样，它使用CharsetEncoder来写回该数据。
 * 
 * @version 1.00 2010-6-1, 17:06:30
 * @since 1.5
 * @author ZhangShixi
 */
public class UseCharsets {

    private static final String IN_FILE = "/home/hjy/copy.sql";
    private static final String OUT_FILE = "/home/hjy/copy.txt";
    private static final String CHARSET_NAME = "ISO-8859-1";

    public static void main(String[] args) throws Exception {
        RandomAccessFile inFile = new RandomAccessFile(IN_FILE, "r");
        RandomAccessFile outFile = new RandomAccessFile(OUT_FILE, "rw");

        FileChannel fcin = inFile.getChannel();
        FileChannel fcout = outFile.getChannel();

        long length = new File(IN_FILE).length();
        MappedByteBuffer inputData = fcin.map(MapMode.READ_ONLY, 0, length);

        Charset charset = Charset.forName(CHARSET_NAME);
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inputData);

        // Process char data here ...

        ByteBuffer buteBuffer = encoder.encode(charBuffer);
        fcout.write(buteBuffer);

        fcin.close();
        fcout.close();
        inFile.close();
        outFile.close();
    }
}
