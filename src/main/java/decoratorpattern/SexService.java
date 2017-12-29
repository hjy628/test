package decoratorpattern;

/**
 * Created by hjy on 17-12-29.
 * 基础抽象类
 */
public abstract class SexService {

    String description = "Best Service";

    public String getDescription(){
        return description;
    }
    public abstract int cost();

}
