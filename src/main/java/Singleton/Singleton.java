package Singleton;

/**
 * Created by hjy on 16-2-17.
 */
public class Singleton {
    //1.
/*
    public Singleton() {}

    private static final Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
*/

    //2.
/*
    private static Singleton instance = null;

    public static synchronized Singleton getInstance(){
        if (instance==null)
            instance=new Singleton();  //lazy load
        return instance;
    }
*/


    //3.

    private static volatile Singleton instance = null;
/*
    public static Singleton getInstance() {
        if (instance == null)     //double check (jdk1.5+)
            synchronized (Singleton.class){
        if (instance == null)
            instance = new Singleton();
    }
        return instance;
    }
*/

    //4.
    private static final class Holder{  //lazy class
        static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance(){
        return Holder.instance;
    }
}
