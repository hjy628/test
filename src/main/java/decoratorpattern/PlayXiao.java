package decoratorpattern;

/**
 * Created by hjy on 17-12-29.
 * 继承自基础类的用来修饰本体的类
 */
public class PlayXiao extends SexService{

    SexService sexService;

    public PlayXiao(SexService sexService) {
        this.sexService = sexService;
    }

    @Override
    public String getDescription() {
        return sexService.getDescription() + "PlayXiao";
    }

    @Override
    public int cost() {
        return sexService.cost()+50;
    }
}
