package other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by hjy on 17-2-15.
 */
public class SerializableTest {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            AObject object = new AObject();
            long beginTime = System.currentTimeMillis();
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeObject(object);
            objectOutput.close();
            byteOutput.close();
            byte[] bytes = byteOutput.toByteArray();
            System.out.println("Java序列化耗时： " + (System.currentTimeMillis() - beginTime) + "ms");
            System.out.println("Java序列化后的字节大小为： "+bytes.length);

            beginTime = System.currentTimeMillis();
            ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInput = new ObjectInputStream(byteIn);
            objectInput.readObject();
            objectInput.close();
            byteIn.close();
            System.out.println("Java反序列化耗时： " + (System.currentTimeMillis() - beginTime) + "ms");
        }
    }

}
