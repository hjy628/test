package chap1;

/**
 * Created by hjy on 18-3-6.
 */
public class EscapeAnalysisClass {
    public static B b;

    public void globalVariablePointerEscape(){  //给全局变量赋值，发生逃逸
        b = new B();
    }

    public B methodPointerEscape(){ //方法返回值，发生逃逸
        return new B();
    }


    public void instancePassPointerEscape(){
        methodPointerEscape().printClassName(this.new G()); //实例引用发生逃逸
    }




    class B{

        public void printClassName(G g){
            System.out.println(g.getClass().getName());
        }

    }

    public class G {
        public  B b;
        public void globalVariablePointerEscape(){  //给全局变量赋值，发生逃逸
            b = new B();
        }

        public B methodPointerEscape(){ //方法返回值，发生逃逸
            return new B();
        }

        public void instancePassPointerEscape(){
            methodPointerEscape().printClassName(this); //实例引用发生逃逸
        }


    }



}
