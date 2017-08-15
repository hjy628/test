package chap4;

/**
 * Created by hjy on 17-7-18.
 */
public class StringLengthComparator {

    public static StringLengthComparator INSTANCE = new StringLengthComparator();


    public StringLengthComparator() {
    }


    public int compare(String s1, String s2){
        return s1.length() - s2.length();
    }

}
