package collections;

import exceptions.QueueEmptyException;
import exceptions.QueueFullException;

/**
 * 队列接口
 *
 * @author focusxyhoo
 * @date 2019-05-17 20:49
 */
public interface Queue {

    int getSize();

    boolean isEmpty();

    void enqueue(Object element) throws QueueFullException;

    Object dequeue() throws QueueEmptyException;

    Object front() throws QueueEmptyException;
}
