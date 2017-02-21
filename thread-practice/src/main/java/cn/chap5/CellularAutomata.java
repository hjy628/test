package cn.chap5;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by hjy on 17-1-22.
 */
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, new Runnable() {
            public void run() {
                mainBoard.commitNewValues();
            }
        });

        this.workers=new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard);
        }
    }

    private class Worker implements Runnable{
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        public void run() {

        }
    }
}
