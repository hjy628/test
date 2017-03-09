package chap8;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by hjy on 17-3-9.
 * 将串行递归转换为并行递归
 *
 * sequentialRecursive用深度优先算法遍历一棵树，在每个节点上执行计算并将结果放入一个集合
 * parallelRecursive同样执行深度优先遍历，但它并不是在访问节点时计算，而是为每个节点提交一个任务来完成计算
 * 当parallelRecursive返回时，树中的各个节点都已经访问过了（但是遍历过程仍然是串行的，只有compute调用才是并行执行的）
 * 并且每个节点的计算任务也已经放入Executor的工作队列，parallelRecursive的调用者可以通过以下方式等待所有的结果：
 * 创建一个特定于遍历过程的Executor,并使用shutdown和awaitTermination等方法
 */
public class RecursiveDemo {

    public<T> void  sequentialRecursive(List<Node<T>> nodes, Collection<T> results){
        for (Node<T> n:nodes) {
            results.add(n.compute());
            sequentialRecursive(n.getChildren(),results);
        }
    }


    public<T> void parallelRecursive(final Executor exec,List<Node<T>> nodes,final Collection<T> results){
        for (final Node<T> n:nodes) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    results.add(n.compute());
                }
            });
            parallelRecursive(exec,n.getChildren(),results);
        }
    }

    public<T> Collection<T> getParallelResults(List<Node<T>> nodes)throws InterruptedException{
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedDeque<T>();
        parallelRecursive(exec,nodes,resultQueue);
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return resultQueue;
    }



    class Node<T>{

        public T compute(){
            return null;
        }

        public List<Node<T>> getChildren(){
            return Collections.EMPTY_LIST;
        }
    }

}
