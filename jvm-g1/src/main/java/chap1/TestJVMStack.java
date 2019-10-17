package chap1;

/**
 * Created by hjy on 18-3-6.
 */
public class TestJVMStack {

    private int count = 0;
    //没有出口的递归函数
    public void recursion(){
        count++;    //每次调用深度加1
        recursion();    //递归调用
    }

    public void testStack(){
        try {
            recursion();
        }catch (Throwable e){
            System.out.println("deep of stack is "+count);  //打印栈溢出的深度
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestJVMStack test = new TestJVMStack();
        test.testStack();
    }


}
