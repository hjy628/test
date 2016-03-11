package concurrent.ch03;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by hjy on 15-8-17.
 */
public class Main3_5 {
    public static void main(String[] args) {
        final int ROWS=10000;
        final int NUMBERS=1000;
        final int SEARCH=5;
        final int PARTICIPANTS=5;
        final int LINES_PARTICIOANT=2000;
        MatrixMock mock=new MatrixMock(ROWS,NUMBERS,SEARCH);
        Results results=new Results(ROWS);
        Grouper grouper=new Grouper(results);
        CyclicBarrier barrier=new CyclicBarrier(PARTICIPANTS,grouper);
        Searcher searchers[]=new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i]=new Searcher(i*LINES_PARTICIOANT,(i*LINES_PARTICIOANT)+LINES_PARTICIOANT,mock,results,5,barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");

    }
}
