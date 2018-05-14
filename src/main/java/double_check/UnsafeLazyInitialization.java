package double_check;


public class UnsafeLazyInitialization {

    private static Instance instance;


    public static Instance getInstance(){
        if (instance==null)        //A线程执行，A可能会看到instance引用的对象还没有完成初始化
            instance = new Instance();  //B线程执行
            return instance;
    }

}
