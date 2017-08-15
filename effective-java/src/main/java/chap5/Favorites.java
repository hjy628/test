package chap5;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjy on 17-7-18.
 */
public class Favorites {

    private Map<Class<?>,Object> favorites = new HashMap<Class<?>,Object>();

    public <T> void putFavorite(Class<T> type,T instance){
        if (type==null){
            throw new NullPointerException("type is null");
        }
        favorites.put(type,instance);

    }

    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }

}
