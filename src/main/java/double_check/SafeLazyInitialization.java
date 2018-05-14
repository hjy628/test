package double_check;


public class SafeLazyInitialization {

    private static Instance instance;

    /**
    *  有性能开销
    * */

    public synchronized static Instance getInstance(){
        if (instance==null)
            instance = new Instance();
            return instance;
    }

}
