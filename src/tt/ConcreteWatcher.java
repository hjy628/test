package tt;

/**
 * Created by hjy on 16-4-6.
 */
public class ConcreteWatcher implements  Watcher{

    @Override
    public void update(String str) {
        System.out.println(str);
    }
}
