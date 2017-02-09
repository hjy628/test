package cn.chap8;

/**
 * Created by hjy on 17-2-8.
 */
public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
