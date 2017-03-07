package chap7;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hjy on 17-3-7.
 * 添加可靠的取消操作
 *
 * 另一种关闭LogWriter的方法是：设置某个“已请求关闭”标志，以避免进一步提交日志消息，在收到关闭请求后，消费者会把队列中的所有消息写入日志，并解除所有在调用Log时
 * 阻塞的生产者，然而，在这个方法中存在着竟态条件问题，使得该方法并不可靠，log的实现是一种“先判断再运行”的代码序列：生产者发现该服务还没有关闭，
 * 因此在关闭服务后仍然会将日志消息放入队列，这同样会使得生产者可能在调用log时阻塞并且无法解除阻塞状态，可以通过一些技巧来降低这种情况的发生概率（例如，
 * 在宣布队列被清空之前，让消费者等待数秒钟），但这些都没有解决问题的本质，即使很小的概率也可能导致程序发生故障
 *
 * 为LogWriter提供可靠关闭操作的方法是解决竞态条件问题，因而要使日志消息的提交操作成为原子操作，然而我们并不希望在消息加入队列时去持有一个锁，
 * 因为put方法本身就可以阻塞。我们采用的方法是：通过原子方式来检查关闭请求，并且有条件地递增一个计数器来“保持”提交消息的权利。
 */
public class LogService {

    private final LoggerThread loggerThread;
    private final BlockingQueue<String> queue;
    private final PrintWriter writer;
    private boolean isShutdown;
    private int reservations;

    public LogService(LoggerThread loggerThread, BlockingQueue<String> queue, Writer writer) {
        this.loggerThread = loggerThread;
        this.queue = queue;
        this.writer = (PrintWriter)writer;
    }

    public void start(){
        loggerThread.start();
    }

    public void stop(){
        synchronized (this){isShutdown = true;}
        loggerThread.interrupt();
    }

    public void log(String msg)throws InterruptedException{
        synchronized (this){
            if (isShutdown)
                throw new IllegalStateException("----");
            ++reservations;
        }
        queue.put(msg);
    }


    private class LoggerThread extends Thread{

        @Override
        public void run() {
            try {
                while (true)
                  try {
                      synchronized (LogService.this){
                          if (isShutdown && reservations ==0)
                              break;
                      }
                      String msg = queue.take();
                      synchronized (LogService.this){
                          --reservations;
                      }
                      writer.println(msg);
                  }catch (InterruptedException e){/*  retry */}
            }
            finally {
                writer.close();
            }
        }
    }
}
