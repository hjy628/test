package other;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hjy on 16-3-14.
 */
public class ChineseSortCompare {
    @SuppressWarnings("rawtypes")
    private final static Comparator CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);

    public static void main(String []args) {
        sortArray();
        sortList();
        System.out.println("李四".compareTo("张三"));//前者大于后者，则为正数，否则为负数，相等为0
    }

    @SuppressWarnings("unchecked")
    private static void sortList() {
        List<String> list = Arrays.asList("张三", "李四", "王五");
        Collections.sort(list, CHINA_COMPARE);
        for(String str : list) {
            System.out.println(str);
        }
    }

    @SuppressWarnings("unchecked")
    private static void sortArray() {
        String[] arr = {"张三", "李四", "王五"};
        Arrays.sort(arr, CHINA_COMPARE);
        for(String str : arr) {
            System.out.println(str);
        }
    }
}
