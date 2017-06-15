package chap5;

/**
 * Created by hjy on 17-5-9.
 */
public class Test {
    public static void main(String[] args) {
        String s = "hello";
        String t = "hello";
        Character c[] = {'h','e','l','l','o'};
        System.out.println(s.equals(t));
        System.out.println(t.equals(c));
        System.out.println(s==t);
        System.out.println(s.equals(t));
    }
}
