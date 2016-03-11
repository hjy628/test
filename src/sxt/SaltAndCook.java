package sxt;

/**
 * Created by hjy on 16-3-9.
 */
public class SaltAndCook {
    public static void main(String[] args) {
        Salt salt = new Salt();
        Mother m = new Mother(salt);
        Son son = new Son(salt);
        new Thread(m).start();
        new Thread(son).start();
    }



    /**
     * 题目： 模拟妈妈做饭，做饭时发现没有盐了，让儿子去买盐
     *       只有盐买回来之后，妈妈才能继续做饭的过程。
     * @author Administrator
     *
     */

//盐
  static   class Salt{
        private int saltNum=0; //盐的数量，假设开始没有盐

        //煮菜需要食用盐,假设煮一个菜需要10克盐
        public synchronized int subSalt(){
            while(saltNum<=0){ //盐没有了，则需要等待
                System.out.println("盐不够了，等待中.....");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            notify();
            saltNum = saltNum - 10;
            System.out.println("妈妈煮菜使用了10克盐！剩余"+saltNum+"克盐！");
            return saltNum;
        }
        //买盐，每次买saltNum克盐
        public synchronized void addSalt(int num){
            while(saltNum>=10){ //还有盐，暂时不需要买
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try { //假设买盐需要10秒钟
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            saltNum = saltNum + num;
            System.out.println("儿子买回来100克盐！");
            notify();//买完盐回来后，唤醒正在等待的母亲继续煮菜
        }
    }

    //母亲
  static   class Mother implements Runnable{
        private Salt salt;
        public Mother(Salt salt) {
            this.salt = salt;
        }
        public void run() {
            while(true){
                salt.subSalt();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //儿子
  static   class Son implements Runnable{
        private Salt salt;
        public Son(Salt salt) {
            this.salt = salt;
        }
        public void run() {
            while(true){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                salt.addSalt(100);
            }
        }

    }



}
