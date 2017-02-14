package other;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by hjy on 17-2-14.
 */
public class SerializableExample {

    public static void main(String[] args) throws Exception{
        Object object = new Object();

        //创建一个字节数组输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        //将字节数组输出流包装为objectOutputStream
        ObjectOutputStream objectOut = new ObjectOutputStream(outputStream);

        //将对象写入ObjectOutputStream
        objectOut.writeObject(object);
        objectOut.close();
        outputStream.close();
        //返回字节数组输出流中的字节数组
        //return outputStream.toByteArray();


    }

}
