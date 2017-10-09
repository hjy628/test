package seckill;


import com.sun.deploy.net.HttpRequest;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hjy on 17-10-9.
 */
public class RequestQueue {

    public static ConcurrentLinkedQueue HttpRequest;
    public static  ConcurrentLinkedQueue<com.sun.deploy.net.HttpRequest> queue = new ConcurrentLinkedQueue();

}
