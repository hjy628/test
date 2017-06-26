package completablefuture;

import static completablefuture.Shop.delay;

/**
 * Created by hjy on 17-6-26.
 */
public class Discount {

    public enum Code{
        NONE(0),SILVER(5),GOLD(10),PLATINUM(15),DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }



    public static String applyDiscount(Quote quote){
        return quote.getShopName()+"Price is "+ Discount.apply(quote.getPrice(),quote.getDiscountCode()); //将折扣代码应用于商品最初的原始价格
    }


    private static double apply(double price,Code code){
        delay();
        return price*(100 - code.percentage) /100;
    }
}
