package cn.chap8;

/**
 * Created by hjy on 17-2-8.
 */
public class Guardian implements Observer{
    @Override
    public void notify(String tweet) {
        if (tweet!=null&&tweet.contains("queen")){
            System.out.println("Breaking another news in london... " + tweet);
        }
    }
}
