package collections;

import exceptions.QueueEmptyException;

/**
 * 基于双向链表的双端队列的实现。
 * 所有操作的时间复杂度都是 O(1)。
 * 为了方便，我们设置了两个哨兵节点，header 和 tailer。它们不存储数据对象。
 *
 * @author focusxyhoo
 * @date 2019-05-23 16:00
 */
public class DequeOnDLNode implements Deque {

    private DLNode header;
    private DLNode tailer;
    private int    size;

    // 构造方法
    public DequeOnDLNode() {
        header = new DLNode();
        tailer = new DLNode();
        header.setNext(tailer);
        tailer.setPrev(header);
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    @Override
    public Object first() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Empty");
        }
        return header.getNext().getElement();
    }

    @Override
    public Object last() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Empty");
        }
        return tailer.getPrev().getElement();
    }

    @Override
    public void insertFirst(Object object) {
        DLNode second = header.getNext();
        DLNode first = new DLNode(object, header, second);
        second.setPrev(first);
        header.setNext(first);
        size++;
    }

    @Override
    public void insertLast(Object object) {
        DLNode second = tailer.getPrev();
        DLNode first = new DLNode(object, second, tailer);
        second.setNext(first);
        tailer.setPrev(first);
        size++;
    }

    @Override
    public Object removeFirsr() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Empty");
        }
        DLNode first = header.getNext();
        DLNode second = first.getNext();
        header.setNext(second);
        second.setPrev(header);
        size--;
        return first.getElement();
    }

    @Override
    public Object removeLast() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Empty");
        }
        DLNode first = tailer.getPrev();
        DLNode second = first.getPrev();
        tailer.setPrev(second);
        second.setNext(tailer);
        size--;
        return first.getElement();
    }

    @Override
    public void traversal() {
        DLNode p = header.getNext();
        while (tailer != p) {
            System.out.print(p.getElement() + " ");
            p = p.getNext();
        }
        System.out.println();
    }
}
