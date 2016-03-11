package concurrent.ch04;

/**
 * Created by hjy on 15-8-18.
 */
public class Main4_3 {
    public static void main(String[] args) {
        Server3 server=new Server3();
        for (int i = 0; i < 100; i++) {
            Task task=new Task("Task "+ i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
