package decoratorpattern;

/**
 * Created by hjy on 17-12-29.
 */
public class SexServiceTest {


    public static void main(String[] args) {

        Nurse sweetHeart = new Nurse();
        SexService sexService = new PlayXiao(sweetHeart);

        System.out.println(sexService.getDescription());
        System.out.println(sexService.cost());

    }


}
