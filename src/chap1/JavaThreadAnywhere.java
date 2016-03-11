package chap1;

/**
 * Created by hjy on 16-1-10.
 */
public class JavaThreadAnywhere {
    public static void main(String[] args) {
        System.out.println("The main method was executed by thread:"+Thread.currentThread().getName());
        Helper helper =new Helper("java thread anywhere");
        helper.run();

    }

    static class Helper implements Runnable{
        private final String message;

        public Helper(String message) {
            this.message = message;
        }

        private void doSomething(String message){
            System.out.println("The doSomething method was executed by thread:"+Thread.currentThread().getName());
            System.out.println("Do something with "+message);
        }

        @Override
        public void run() {
            doSomething(message);
        }
    }


}
