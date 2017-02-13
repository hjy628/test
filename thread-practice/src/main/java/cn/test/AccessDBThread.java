package cn.test;

/**
 * Created by hjy on 17-2-13.
 */
public class AccessDBThread implements Runnable{

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AccessDBThread() {
        super();
    }

    public AccessDBThread(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        //像数据库中添加msg变量值
        System.out.println("Added the message: "+msg+" into the Database");
    }
}
