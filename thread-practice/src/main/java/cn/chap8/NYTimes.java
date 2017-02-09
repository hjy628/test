package cn.chap8;

/**
 * Created by hjy on 17-2-8.
 */
public class NYTimes implements Observer{
    @Override
    public void notify(String tweet) {
        if (tweet!=null&&tweet.contains("money")){
            System.out.println("Breaking news in NY!" + tweet);
        }
    }
}
