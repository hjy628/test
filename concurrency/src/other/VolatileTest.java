package other;

/**
 * Created by hjy on 17-3-8.
 * 依据happens-before原则，程序得到如下关系：
 * 依据happens-before程序顺序原则：1 happens-before 2、3 happens-before 4；
 * 根据happens-before的volatile原则：2 happens-before 3；
 * 根据happens-before的传递性：1 happens-before 4
 * volatile除了保证可见性外，还有就是禁止重排序。所以A线程在写volatile变量之前所有可见的共享变量，在线程B读同一个volatile变量后，将立即变得对线程B可见。
 */
public class VolatileTest {
    int i = 0;
    volatile boolean flag = false;

    //Thread A
    public void write(){
        i = 2;              //1
        flag = true;        //2
    }

    //Thread B
    public void read(){
        if(flag){                                   //3
            System.out.println("---i = " + i);      //4
        }
    }
}
