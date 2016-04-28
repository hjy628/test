package other;

/**
 * Created by hjy on 16-3-17.
 */
public class JVMMethodStack {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loop(0);
            }

            private void loop(int i){
                if (i!=1800){
                    i++;
                    loop(i);
                }
                else {
                    return;
                }
            }
        }).start();

    }
}
