package cn.chap15;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hjy on 17-8-29.
 * Michael-Scott(Michael and Scott,1996)非阻塞算法中的插入算法
 *
 * 当插入一个元素时，需要更新两个指针.首先更新当前最后一个元素的next指针，将新节点链接到列表队尾，然后更新尾节点，将其指向这个新元素
 * 在这两个操作之间，队列处于一种中间状态， 在第二次更新完成后，队列将再次处于稳定状态
 * 实现这两个技巧的关键点在于：当队列处于稳定状态时，尾节点的next域将为空，如果队列处于中间状态，那么tail.next将为非空
 * 因此，任何线程都能够通过检查tail.next来获取队列当前的状态
 * 而且，当队列处于中间状态时，可以通过将尾节点向前移动一个节点，从而结束其他线程正在执行的插入元素操作，并使得队列恢复为稳定状态
 *
 *LinkedQueue.put方法在插入新元素之前，将首先检查队列是否处于中间状态(步骤A)。
 * 如果是，那么有另一个线程正在插入元素(在步骤C和D之间).此时当前线程不会等待其他线程执行完成，而是帮助它完成操作
 * 并将尾节点向前推进一个节点(步骤B).然后，它将重复执行这种检查，以免另一个线程已经开始插入新元素，并继续推进尾节点,
 * 直到它发现队列处于稳定状态后，才会开始执行自己的插入操作
 *
 * 由于步骤C中的CAS将把新节点链接到队列尾部，因此如果两个线程同时插入元素，那么这个CAS将失败，在这样的情况下，并不会造成破坏：不会发生任何变化
 * 并且当前的线程只需重新读取尾节点并再次重试。如果步骤C成功了，那么插入操作将生效，第二个CAS（步骤D）被认为是一个"清理操作"
 * 因为它既可以由执行插入操作的线程来执行，也可以由其他任何线程来执行。如果步骤D失败，那么执行插入操作的线程将返回
 * 而不是重新执行CAS,因为不需要重试--另一个线程已经在步骤B中完成了这个工作.这种方式能够工作，因为在任何线程尝试将一个新节点插入到队列之前，
 * 都会首先通过检查tail.next是否非空来判断是否需要清理队列。如果是，它首先会推进尾节点（可能需要执行多次）,直到队列处于稳定状态
 *
 *
 */
public class LinkedQueue<E> {

    private static class Node<E>{
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, AtomicReference<Node<E>> next) {
            this.item = item;
            this.next = next;
        }
    }

    private final Node<E> dummy = new Node<E>(null,null);
    private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);

    public boolean put(E item){
        Node<E> newNode = new Node<E>(item,null);
        while (true){
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail==tail.get()){
                if (tailNext!=null){            //A
                    //队列处于中间状态，推进尾节点
                    tail.compareAndSet(curTail,tailNext);       //B
                }else {
                    //处于稳定状态，尝试插入新节点
                    if (curTail.next.compareAndSet(null,newNode)){      //C
                        //插入操作成功，尝试推进尾节点
                        tail.compareAndSet(curTail,newNode);        //D
                        return true;
                    }
                }
            }
        }
    }


}
