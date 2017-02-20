package other;

/**
 * Created by hjy on 17-2-20.
 */
public class StackOverFlowExample {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loop(0);
            }

            private void loop(int i){
                if (i!=10000){
                    i++;
                    loop(i);
                }else {
                    return;
                }
            }
        }).start();
    }

}
