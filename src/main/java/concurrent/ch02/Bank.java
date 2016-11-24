package concurrent.ch02;

/**
 * Created by hjy on 15-6-25.
 */
public class Bank implements Runnable{
    private Account account;
    public Bank(Account account){
        this.account=account;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            account.subtractAmount(1000);
        }
    }
}
