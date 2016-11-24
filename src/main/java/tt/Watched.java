package tt;

/**
 * Created by hjy on 16-4-6.
 */
public interface Watched {
    public void addWatcher(Watcher watcher);

    public void removeWatcher(Watcher watcher);

    public void notifyWatchers(String str);
}
