package other;

/**
 * Created by hjy on 17-2-23.
 */
public class Test {
    public static void main(String[] args) {
        boolean[] lights = new boolean[100];
        for (int i = 0; i < 100; i++) {
            lights[i] = true;
        }

        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j < lights.length; j++) {
                if (j%(i)==0){
                    lights[j] =!lights[j];
                    System.out.print(j+":"+lights[j]);
                }
            }
            System.out.println("\n");
        }


        for (int i = 0; i < lights.length; i++) {
            System.out.print(i+":"+lights[i]);
        }

    }
}
