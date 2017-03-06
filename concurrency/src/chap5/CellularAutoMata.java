package chap5;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by hjy on 17-3-6.
 * 通过CyclicBarrier协调细胞自动衍生系统中的计算
 */
public class CellularAutoMata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;

    public CellularAutoMata(Board mainBoard, CyclicBarrier barrier) {
        this.mainBoard = mainBoard;
        this.barrier = barrier;
    }

    public void start(){

    }


    private class Worker implements Runnable{
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        @Override
        public void run() {
            while (!board.hasConverged()){
                for (int i = 0; i <board.getMaxX() ; i++) {
                    for (int j = 0; j <board.getMaxY(); j++) {
                        board.setNewValue(i,j,computeValue(i,j));
                    }
                }
            }
        }

        private boolean computeValue(int i, int j) {
            return i==j;
        }
    }


    class Board{
        private int X;
        private int y;
        public boolean hasConverged(){
            return false;
        }


        public int getMaxX(){
            return 10;
        }
        public int getMaxY(){
            return 10;
        }

        public void setNewValue(int x,int y,boolean value){

        }
    }
}
