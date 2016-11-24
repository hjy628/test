package concurrent.ch02;

/**
 * Created by hjy on 15-8-17.
 */
public class Producer8 implements Runnable{
    private FileMock mock;
    private Buffer buffer;

    public Producer8(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLines()){
            String line = mock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
