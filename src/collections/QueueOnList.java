package collections;

import exceptions.QueueEmptyException;
import exceptions.QueueFullException;

/**
 * 基于单链表的队列的实现。
 * 出于效率的考虑，这里以单链表的头节点作为队列的首节点，这样可以回避单链表在尾部进行删除操作时的效率低下（时间复杂度为 O(n)）。
 * 所有操作的时间复杂度均为 O(1)。
 *
 * @author focusxyhoo
 * @date 2019-05-23 15:28
 */
public class QueueOnList implements Queue {

    private Node head;
    private Node tail;
    private int size;

    public QueueOnList() {
        head = tail = null;
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
    public void enqueue(Object element) throws QueueFullException {
        Node node = new Node();
        node.setElement(element);
        node.setNext(null);

        if (0 == size)
            head = node;
        else tail.setNext(node);
        tail = node;
        size++;
    }

    @Override
    public Object dequeue() throws QueueEmptyException {
        if (0 == size)
            throw new QueueEmptyException("Queue Empty");
        Object object = head.getElement();
        head = head.getNext();
        size--;
        if (0 == size) { // 如果队列已空，需要将末节点置空。
            tail = null;
        }
        return object;
    }

    @Override
    public Object front() throws QueueEmptyException {
        if (isEmpty())
            throw new QueueEmptyException("Queue Empty");
        return head.getElement();
    }

    public void traversal() {
        Node p = head;
        while (null != p) {
            System.out.print(p.getElement() + " ");
            p = p.getNext();
        }
    }
}
