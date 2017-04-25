package core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by hjy on 17-4-25.
 */
public class NetClassLoader extends ClassLoader{
    private String classPath;
    private String packageName = "com.hjy.test";

    public NetClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);
        if (aClass!=null){
            return aClass;
        }
        if (packageName.startsWith(name)){
            byte[] classData = getData(name);
            if (classData == null){
                throw new ClassNotFoundException();
            }else {
                return defineClass(name,classData,0,classData.length);
            }
        }else {
            return super.loadClass(name);
        }
    }


    private byte[] getData(String className){
        String path = classPath + File.separatorChar + className.replace(".",File.separator)+ ".class";
        try {
            URL url = new URL(path);
            InputStream is = url.openStream();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1){
                stream.write(buffer,0,num);
            }
            return stream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private byte[] deCode(byte[] src){
        byte[] decode = null;
        //对src字节码进行解码处理
        return decode;
    }


}
