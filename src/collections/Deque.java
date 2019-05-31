package collections;

import exceptions.QueueEmptyException;

/**
 * 双端队列接口的实现。
 *
 * @author focusxyhoo
 * @date 2019-05-23 15:45
 */
public interface Deque {
    // 返回队列中元素数目。
    int getSize();

    //判断队列是否为空。
    boolean isEmpty();

    // 返回队列首元素（不删除）。
    Object first() throws QueueEmptyException;

    // 返回队尾末元素（不删除）。
    Object last() throws QueueEmptyException;

    // 将新元素作为首元素插入列表。
    void insertFirst(Object object);

    // 将新元素作为末元素插入列表。
    void insertLast(Object object);

    // 删除首元素。
    Object removeFirsr() throws QueueEmptyException;

    // 删除末元素。
    Object removeLast() throws QueueEmptyException;

    // 遍历。
    void traversal();
}
