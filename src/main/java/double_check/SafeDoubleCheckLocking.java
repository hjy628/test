package double_check;


public class SafeDoubleCheckLocking {   //1

    private volatile static Instance instance;   //2

    /**
    *  1:多个线程试图在同一时间创建对象时，会通过加锁来保证只有一个线程能够创建对象
     *  2:在对象创建好之后，执行getInstance()方法将不需要获取锁，直接返回已创建好的对象
     *  但是是一个错误的优化，在线程执行到第4行，代码读取到instance不为null时，instance引用的对象可能还没有完成初始化
     *
     *  第七行可以分解为下面3行伪代码:
     *  memory = allocate();    //1:分配对象的内存空间
     *  ctorInstance(memory)    //2:初始化对象
     *  instance = memory       //3:设置instance指向刚分配的内存地址
     *  2和3可能会被冲排序,地址指向分配的内存地址，但对象还没有被初始化
     *
     *
     *  解决问题，两个办法实现线程安全的延迟初始化
     *  1:不允许2和3重排序
    * */

    public  static Instance getInstance(){  //3
        if (instance==null) {                 //4：第一次检查
            synchronized (SafeDoubleCheckLocking.class){    //5: 加锁
                if (instance==null)                     //6:第二次检查
                instance = new Instance();              //7: instance为volatile,现在没问题了 (since JDK5或更高版本，JSR133内存模型规范增强了volatile的语义)
            }                                           //8
        }                                               //9
        return instance;                                //10
    }                                                   //11

}
