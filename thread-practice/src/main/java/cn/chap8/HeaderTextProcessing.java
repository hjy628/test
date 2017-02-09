package cn.chap8;

/**
 * Created by hjy on 17-2-8.
 */
public class HeaderTextProcessing extends ProcessingObject<String>{
    @Override
    protected String handleWork(String input) {
        return "From Raoul,Mario and ALan: "+input;
    }
}
