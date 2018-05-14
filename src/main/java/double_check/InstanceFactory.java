package double_check;


public class InstanceFactory {   //1

    private static class InstanceHolder{
        public static Instance instance = new Instance();
    }

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
     *  2:允许重排序，但不允许其他线程“看到”这个重排序，保证其他线程初始访问对象在2初始化对象之后
     *
     *
     *  JVM在类的初始化阶段（即在Class被加载后，且被线程使用之前）,会执行类的初始化,在执行类的初始化期间，JVM会去获取一个锁，这个锁可以同步多个线程对同一个类的初始化
     *
     *  初始化一个类，包括执行这个类的静态初始化和初始化在这个类中声明的静态字段，
     *  在首次发生下列任一种情况时，一个类或接口类型T将被立即初始化
     *  1: T是一个类，而且一个T类型的实例被创建
     *  2:T是一个类，且T中声明的一个静态方法被调用
     *  3：T中声明的一个静态字段被赋值
     *  4: T中声明的一个静态字段被使用,而且这个字段不是一个常量字段
     *  5: T是一个顶级类,而且一个断言语句嵌套在T内部被执行
     *
     *
     *
     *
     *
    * */

    public  static Instance getInstance() {
        return InstanceHolder.instance;

    }

}
