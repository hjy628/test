package cn.chap8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-8.
 */
public class Feed implements Subject{
    private final List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
            observers.forEach(observer -> observer.notify(tweet));
    }


    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMode());
        f.notifyObservers("The queen said her favurite book is java 8 in Action!");
    }
}
