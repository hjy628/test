package concurrent.ch03;

/**
 * Created by hjy on 15-8-17.
 */
public class Main3_4 {
    public static void main(String[] args) {
        Videoconference videoconference = new Videoconference(10);
        Thread threadConference=new Thread(videoconference);
        threadConference.start();
        for (int i = 0; i < 10; i++) {
            Participant p =new Participant(videoconference,"Participant "+i);
            Thread t = new Thread(p);
            t.start();
        }
    }
}
