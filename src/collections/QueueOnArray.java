package collections;

import exceptions.QueueEmptyException;
import exceptions.QueueFullException;

/**
 * 采用定长数组来实现队列。
 * 应用：队列的结构很实用用来实现循环分配器：
 * 即按照环形次序反复循环，为共享某一资源的一群客户做资源分配。
 * 最常见的例子就是共享一个 CPU 的多个应用程序了。
 *
 * @author focusxyhoo
 * @date 2019-05-23 14:49
 */
public class QueueOnArray implements Queue {

    // 默认数组容量
    public static final int CAPALITY = 1000;
    // 数组的实际容量
    private int capality;
    // 对象数组
    private Object[] queue;
    // 队首元素位置
    private int first = 0;
    // 队尾元素位置
    private int end = 0;

    // 构造方法，带参数与不带参数
    public QueueOnArray() {
        this(CAPALITY);
    }

    public QueueOnArray(int capality) {
        this.capality = capality;
        queue = new Object[this.capality];
    }

    // 查询当前队列的规模
    @Override
    public int getSize() {
        return (capality - first + end) % capality;
    }

    @Override
    public boolean isEmpty() {
        return first == end;
    }

    @Override
    public void enqueue(Object element) throws QueueFullException {

        if (getSize() == capality - 1) {
            throw new QueueFullException("Queue Overflow");
        }
        queue[end] = element;
        end = (end + 1) % capality; // 这一步是不是有点多余？只要end还比capability小，求余似乎没有意义。
    }

    @Override
    public Object dequeue() throws QueueEmptyException {
        Object element;
        if (isEmpty())
            throw new QueueEmptyException("Queue Empty");
        element = queue[first];
        queue[first] = null;
        first = (first + 1) % capality;
        return element;
    }

    @Override
    public Object front() throws QueueEmptyException {
        if (isEmpty())
            throw new QueueEmptyException("Queue Empty");
        return queue[first];
    }

    // 遍历
    public void traversal() {
        for (int i = first; i < end; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }
}
