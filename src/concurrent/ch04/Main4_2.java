package concurrent.ch04;

/**
 * Created by hjy on 15-8-18.
 */
public class Main4_2 {
    public static void main(String[] args) {
        Server server=new Server();
        for (int i = 0; i < 100; i++) {
            Task task=new Task("Task "+ i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
