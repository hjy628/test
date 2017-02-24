package other;

/**
 * Created by hjy on 17-2-24.
 */
public class House {

        //对于非静态成员变量，谁先定义谁就先被初始化。顺序为w1、w2、w3
        Window w1 = new Window(1);
        public House() {
            System.out.println("House()");
            w3 = new Window(33);
        }
        Window w2 = new Window(2);
        void f(){
            System.out.println("f()");
        }
        static void f1(){
            System.out.println("static f1()");
        }
        Window w3 = new Window(3); //在函数f后面，但是在f调用之前得到了初始化
        static Window w4 = new Window(4);  //静态成员变量最先被初始化
        /**
         * @param args
         */
        public static void main(String[] args) {
            House h = new House();
            h.f();
            House.f1();
        }

    }
    class Window{
        public Window(int maker) {
            System.out.println("Window("+maker+")");
        }

}
