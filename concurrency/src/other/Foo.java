package other;

/**
 * Created by hjy on 17-2-20.
 * -server方式执行。
 */
public class Foo {

    public static void main(String[] args) {
        Foo foo = new Foo();
        for (int i = 0; i < 10; i++) {
            foo.bar();
        }
    }

    public void bar(){
        // some bar code;
        for (int i = 0; i < 10700; i++) {
            bar2();
        }
    }

    public void bar2(){
        //bar2 method;
    }

}
