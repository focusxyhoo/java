package collections;

import exceptions.EmptyPriorityQueueException;
import exceptions.InvalidKeyException;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-29
 * time        : 16:50
 * description : 优先队列接口。
 */
public interface PriorityQueue {

    // 统计优先队列的规模
    int getSize();

    // 判断优先队列是否为空
    boolean isEmpty();

    // 若优先队列不为空，返回其中的最小条目；否则报错
    Entry getMin() throws EmptyPriorityQueueException;

    // 将对象 obj 与关键码 k 组合成一个新的条目，并插入到优先队列中，返回该条目
    Entry insert(Object key, Object obj) throws InvalidKeyException;

    // 若优先队列非空，从中删除关键码最小的条目并返回；否则报错
    Entry delMin() throws EmptyPriorityQueueException;
}
