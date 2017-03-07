package chap7;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by hjy on 17-3-7.
 * 不支持关闭的生产者-消费者日志服务
 *
 * 日志操作在单独的线程中执行，产生日志消息的线程并不会将消息直接写入输出流，而是由LogWriter通过BlockingQueue将消息提交给日志线程
 * 并由日志线程写入，这是一种多生产者单消费者(Multiple-Producer,Single-Consumer)的设计方式：每个调用log的操作都相当于一个生产者，
 * 而后台的日志线程则相当于消费者。如果消费者的处理速度低于生产者的生成速度，那么BlockingQueue将阻塞生产者，直到日志线程有能力处理新的日志消息
 * 为了使像LogWriter这样的服务在软件产品中能发挥实际的作用，还需要实现一种终止日志线程的方法，从而避免使JVM无法正常关闭。要停止日志线程是
 * 很容易的，因为它会反复调用take,而take能响应中断。如果将日志线程修改为当捕获到InterruptedException时退出，那么只需要中断日志线程就能停止服务,
 * 然而，如果只是使日志线程退出，那么还不是一种完备的关闭机制，这种直接关闭的做法会丢失那些正在等待被写入到日志的信息，不仅如此，其他线程将在调用Log时被
 * 阻塞，因为日志消息队列是满的，因此这些线程将无法解除阻塞状态。当取消一个生产者-消费者操作时，需要同时取消生产者和消费者。
 * 在中断日志线程时会处理消费者，但在这个示例中，由于生产者并不是专门的线程，因此要取消它们将非常困难.
 */
public class LogWriter {

    private final static int APAPCITY = 10 ;
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;


    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingDeque<String>();
        this.logger = new LoggerThread(writer);
    }

    public void start(){logger.start();}

    public void log(String msg) throws InterruptedException{
        queue.put(msg);
    }

    private class LoggerThread extends Thread{
        private final PrintWriter writer;

        public LoggerThread(Writer writer){
            this.writer=(PrintWriter)writer;
        }

        @Override
        public void run() {
            try {
                while (true)
                    writer.println(queue.take());
            }catch (InterruptedException ignored){}
            finally {
                writer.close();
            }

            super.run();
        }
    }
}
