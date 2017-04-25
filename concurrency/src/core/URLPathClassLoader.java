package core;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by hjy on 17-4-25.
 */
public class URLPathClassLoader extends URLClassLoader{

    private String packageName = "com.hjy.test";

    public URLPathClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);
        if (aClass!=null){
            return aClass;
        }
        if (!packageName.startsWith(name)){
            return super.loadClass(name);
        }else {
            return findClass(name);
        }

    }
}
