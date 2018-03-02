package testgc;

/**
 * Created by hjy on 18-3-2.
 */
public class StackOverFlowErrorTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loop(0);
            }

            private void loop(int i){
                if (i!=300000){
                    i++;
                    loop(i);
                }else {
                    return;
                }
            }

        }).start();
    }

}
