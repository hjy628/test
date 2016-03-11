package web;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;

/**
 * Created by hjy on 16-2-26.
 */
public class FileChannel {
    public static void main(String[] args) {

    }

    public static void map(String[] args){
        int BUFFER_SIZE=1024;
        String filename = "test.db";
        long fileLength = new File(filename).length();
        int bufferCount = 1+(int)(fileLength/BUFFER_SIZE);
        MappedByteBuffer[] buffers = new MappedByteBuffer[bufferCount];
        long remaning = fileLength;
        for (int i = 0; i < bufferCount; i++) {
            RandomAccessFile file;
            try {
                file=new RandomAccessFile(filename,"r");
                buffers[i]=file.getChannel().map(java.nio.channels.FileChannel.MapMode.READ_ONLY,i*BUFFER_SIZE,(int)Math.min(remaning,BUFFER_SIZE));
            }catch (Exception e){
                e.printStackTrace();
            }
            remaning-=BUFFER_SIZE;
        }

    }


}
