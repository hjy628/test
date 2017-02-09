package cn.chap8;

/**
 * Created by hjy on 17-2-8.
 */
public class LeMode implements Observer{
    @Override
    public void notify(String tweet) {
        if (tweet!=null&&tweet.contains("wine")){
            System.out.println("Today cheese,wine and news!" + tweet);
        }
    }
}
