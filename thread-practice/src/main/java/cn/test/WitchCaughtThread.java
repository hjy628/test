package cn.test;

/**
 * Created by hjy on 17-2-13.
 */
public class WitchCaughtThread {
      /*  public static void main(String[] args){
            class ExceptionHandler implements Thread.UncaughtExceptionHandler {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println("==Exception: " + e.getMessage());
                }
            }

            Thread thread = new Thread(new NoCaughtThread.Task());
            thread.setUncaughtExceptionHandler(new ExceptionHandler());
            thread.start();

        }
*/



    public static void main(String args[])
    {

        class ExceptionHandler implements Thread.UncaughtExceptionHandler
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                System.out.println("==Exception: "+e.getMessage());
            }
        }

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        Thread thread = new Thread(new NoCaughtThread.Task());
        thread.start();
    }






}
