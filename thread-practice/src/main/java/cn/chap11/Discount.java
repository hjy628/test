package cn.chap11;

import static cn.chap11.Shop.delay;

/**
 * Created by hjy on 17-2-9.
 */
public class Discount {
    public enum Code{
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }


    }

    public static String applyDiscount(Quote quote){
        return quote.getShopName() + "price is " ;
    }

    private static double apply(Double price,Code code){
        delay();
        return price*(100 - code.percentage) /100;
    }

}
