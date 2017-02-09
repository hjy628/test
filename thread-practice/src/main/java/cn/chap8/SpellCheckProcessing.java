package cn.chap8;

/**
 * Created by hjy on 17-2-8.
 */
public class SpellCheckProcessing extends ProcessingObject<String>{
    @Override
    protected String handleWork(String input) {
        return input.replaceAll("labda","lambda");
    }
}
