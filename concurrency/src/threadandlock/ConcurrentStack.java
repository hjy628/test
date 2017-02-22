package threadandlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hjy on 17-2-22.
 * 基于Treiber算法实现的无阻塞的Stack
 * Stack是LIFO方式，因此不能采取类似LinkedBlockingQueue中两把锁的机制，这里巧妙采用AtomicReference来实现了无阻塞的push和pop,
 * 在push时基于AtomicReference的CAS方法来比较目前的head是否一致.
 * 如果不一致，说明有其他线程改动了，如果有改动则继续循环，直到一致，才修改head元素，在pop时可采用同样的方式进行操作
 */
public class ConcurrentStack<E> {

    AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();

    public void push(E item){
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead=head.get();
            newHead.next=oldHead;
        }while (!head.compareAndSet(oldHead,newHead));
    }

    public E pop(){
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null){
                return null;
            }
            newHead = oldHead.next;
        }while (!head.compareAndSet(oldHead,newHead));
        return oldHead.item;
    }



    static class Node<E>{
        final E item;
        Node<E> next;

        public Node(E item) {
            this.item=item;
        }
    }
}
