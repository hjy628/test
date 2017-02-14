/*
package other;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

*/
/**
 * Created by hjy on 17-2-14.
 *//*

public class FutureTaskTest {

    Map<String,Connection> connectionPool = new ConcurrentHashMap<String,Connection>();

    public Connection getConnection(String key){
        FutureTask<Connection> connectionFutureTask = connectionPool.get(key);
        if (connectionFutureTask!=null){
            return connectionFutureTask.get();
        }else {
            Callable<Connection> callable = new Callable<Connection>() {
                @Override
                public Connection call() throws Exception {
                    return null;
                }
            };
            FutureTask<Connection> newTask = new FutureTask<Connection>(callable);
            connectionFutureTask = connectionPool.putIfAbsent(key,newTask);
            if (connectionFutureTask==null){
                connectionFutureTask = newTask;
                connectionFutureTask.run();
            }
            return connectionFutureTask.get();
        }
    }

}
*/
