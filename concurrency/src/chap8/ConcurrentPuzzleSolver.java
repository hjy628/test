package chap8;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * Created by hjy on 17-3-9.
 */
public class ConcurrentPuzzleSolver<P,M> {
    private final Puzzle<P,M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentHashMap<P,Boolean> seen;
    final ValueLatch<Node<P,M>> solution = new ValueLatch<Node<P,M>>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec, ConcurrentHashMap<P, Boolean> seen) {
        this.puzzle = puzzle;
        this.exec = exec;
        this.seen = seen;
    }

    public List<M> solve() throws InterruptedException{
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p,null,null));
            //阻塞直到找到解答
            Node<P,M> solnNode = solution.getValue();
            return (solnNode == null)?null:solnNode.asMoveList();
        }finally {
            exec.shutdown();
        }
    }


    protected Runnable newTask(P p, M m, Node<P,M> n){
        return null;
    }

    class SolverTask extends Node<P,M> implements Runnable{
        @Override
        public void run() {
            if (solution.isSet()||seen.putIfAbsent(pos,true)!=null){
                return;     //已经找到了解答或者已经遍历了这个位置
            }
            if (puzzle.isGoal(pos)){
                solution.setValue(this);
            }else {
                for (M m:puzzle.legalMoves(pos)){
                    exec.execute(newTask(puzzle.move(pos,m),m,this));
                }
            }
        }
    }





    static class  Node<P,M> {
        final P pos;
        final M move;
        final Node<P,M> prev;

        public Node() {
            this.pos = null;
            this.move = null;
            this.prev = null;
        }

        public Node(P pos, M move, Node<P, M> prev) {
            this.pos = pos;
            this.move = move;
            this.prev = prev;
        }

        List<M> asMoveList(){
            List<M> solution = new LinkedList<M>();
            for (Node<P,M> n = this; n.move!=null; n=n.prev)
                solution.add(0,n.move);
            return solution;
        }

    }

}
