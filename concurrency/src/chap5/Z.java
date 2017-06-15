package chap5;

/**
 * Created by hjy on 17-5-9.
 */
public class Z extends X{
    Y y = new Y();

    public Z() {
        System.out.println("Z");
    }

    public static void main(String[] args) {
        new Z();
    }
}




class X{
    Y y = new Y();
    public X() {
        System.out.println("X");
    }
}

class Y{
    public Y() {
        System.out.println("Y");
    }
}
