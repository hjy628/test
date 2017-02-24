package other;

/**
 * Created by hjy on 17-2-24.
 */
public class StringHashCode {
    public static void main(String[] args) {
        String[] hellos = "Hello Hello".split(" " );
        System.out.println(""+hellos[0].hashCode());
        System.out.println(""+hellos[1].hashCode());
        String a = new String("hello");
        String b = new String("hello");
        System.out.println(""+a.hashCode());
        System.out.println(""+b.hashCode());

        StringBuilder s= new StringBuilder();
        StringBuffer s1= new StringBuffer();
    }

}
