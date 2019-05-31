package collections;

import exceptions.EmptyPriorityQueueException;
import exceptions.InvalidKeyException;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-30
 * time        : 10:51
 * description : 基于有序（升序）列表实现的优先队列
 * 为了维护列表的有序性，在插入条目时，必须顺序扫描现有的条目，通过比较确定正确的插入位置。
 * 在最好的情况下，只需比较一次，而在最坏的情况下需要比较 n 次。时间复杂度为 O(n)。
 * 而查询和删除操作的时间复杂度为 O(1)。
 */
public class PriorityQueueOnSortedList implements PriorityQueue {

    private List L;
    private Comparator C;

    /******************************* 构造方法 **************************************/

    // 构造方法，使用默认比较器
    public PriorityQueueOnSortedList() {
        this(new DefaultComparator(), null);
    }

    // 构造方法，直到比较器
    public PriorityQueueOnSortedList(Comparator c) {
        this(c, null);
    }

    // 构造方法，指定初始元素
    public PriorityQueueOnSortedList(Sequence s) {
        this(new DefaultComparator(), s);
    }

    // 构造方法，指定比较器和初始元素
    public PriorityQueueOnSortedList(Comparator c, Sequence s) {
        L = new ListOnDLNode();
        C = c;
        if (null != s) {
            while (!s.isEmpty()) {
                Entry e = (Entry) s.removeFirst();
                insert(e.getKey(), e.getValue());
            }
        }
    }

    /******************************* PriorityQueue 接口方法 **************************************/

    @Override
    public int getSize() {
        return L.getSize();
    }

    @Override
    public boolean isEmpty() {
        return L.isEmpty();
    }

    @Override
    public Entry getMin() throws EmptyPriorityQueueException {
        if (L.isEmpty())
            throw new EmptyPriorityQueueException("优先队列空");
        return (Entry) L.last();
    }

    @Override
    public Entry insert(Object key, Object obj) throws InvalidKeyException {
        Entry entry = new DefaultEntry(key, obj);
        // 当列表为空，或者新条目比列表第一个元素（同时也是最大元素）大时
        if (L.isEmpty() || 0 > C.compare(((Entry) L.first().getElement()).getKey(), entry.getKey())) {
            L.insertFirst(entry); // 直接插入至表头
        } else {
            Position curPos = L.last(); // 从列表尾开始
            while (0 > C.compare(((Entry) curPos.getElement()).getKey(), entry.getKey())) {
                curPos = L.getPrev(curPos); // curPos 不断前移，直到第一个不小于 entry 的条目
            }
            L.insertAfter(curPos, entry); // 将 entry 插入到到该条目之后
        }
        return entry;
    }

    @Override
    public Entry delMin() throws EmptyPriorityQueueException {
        if (L.isEmpty())
            throw new EmptyPriorityQueueException("优先列表空");
        return (Entry) L.remove(L.last());
    }
}
