package cn.chap11;

import sun.dc.pr.PRError;

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

}
