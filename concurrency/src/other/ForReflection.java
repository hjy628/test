package other;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjy on 17-2-20.
 */
public class ForReflection {

    private Map<String,String> caches = new HashMap<String,String>();
    public void execute(String message){
        String b = this.toString()+message;
        caches.put(b,message);
    }

}
