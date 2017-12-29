package decoratorpattern;

/**
 * Created by hjy on 17-12-29.
 * 继承自抽象类的本体
 */
public class Nurse extends SexService{


    public Nurse() {
        description = "you know";
    }

    @Override
    public int cost() {
        return 150;
    }
}
