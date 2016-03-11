package Seven.ch02;

import sun.net.ProgressListener;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by hjy on 15-8-31.
 */
public class Downloader {
    private CopyOnWriteArrayList<ProgressListener> listeners;

    public void addListener(ProgressListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ProgressListener listener) {
        listeners.remove(listener);
    }

    private void updateProgress(int n){
        for (ProgressListener listener: listeners)
         //   listener.onProgress(n);
            System.out.println("xxx");
    }
}
