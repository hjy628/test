package core;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hjy on 17-4-25.
 */
public class ClassReloader extends ClassLoader{
    private String classPath;
    String classname = "core";

    public ClassReloader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData==null){
            throw new ClassNotFoundException();
        }else {
            return defineClass(classname,classData,0,classData.length);
        }
    }

    private byte[] getData(String className){
        String path = classPath + className;
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1){
                stream.write(buffer,0,num);
            }
            return stream.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            String path = "/home/hjy/IdeaProjects/IDEA/test/classes/production/concurrency/";
            ClassReloader reloader = new ClassReloader(path);
            Class r = reloader.findClass("Test.class");
            System.out.println(r.newInstance());
            ClassReloader reloader2 = new ClassReloader(path);
            Class r2 = reloader2.findClass("Test.class");
            System.out.println(r2.newInstance());
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
