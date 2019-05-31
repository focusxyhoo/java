package collections;

import exceptions.EmptyPriorityQueueException;
import exceptions.InvalidKeyException;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-30
 * time        : 10:30
 * description : 基于无序列表实现的优先队列。
 *               时间复杂度分析：插入时直接插到列表后面，为O(1)。
 *               但是在删除或者查询时，都需要对列表所有元素进行比较，才能得到最小的元素并返回，为O(n)。
 *               注意：当采用向量结构来实现优先队列时，无论有序还是无序，都无法兼顾插入和删除操作的时间复杂度。
 *               因此只能适用于某一操作远多余另一操作的情形。
 */
public class PriorityQueueOnUnsortedList implements PriorityQueue {
    private List L;
    private Comparator C;

    /******************************* 构造方法 **************************************/
    // 构造方法，指定比较器
    public PriorityQueueOnUnsortedList(Comparator c) {
        this(c, null);
    }

    // 构造方法，指定初始元素
    public PriorityQueueOnUnsortedList(Sequence s) {
        this(new DefaultComparator(), s);
    }

    // 构造方法，指定比较器和初始元素
    public PriorityQueueOnUnsortedList(Comparator c, Sequence s) {
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
        Position minPos = L.first();
        Position curPos = L.getNext(minPos);
        while (null != curPos) {
            if (0 < C.compare(minPos.getElement(), curPos.getElement())) {
                minPos=curPos;
            }
        }
        return (Entry) minPos.getElement();
    }

    @Override
    public Entry insert(Object key, Object obj) throws InvalidKeyException {
        Entry entry = new DefaultEntry(key, obj);
        // 因为是基于无序列表实现的，因此在插入时直接插在列表后面即可
        L.insertLast(entry);
        return entry;
    }

    @Override
    public Entry delMin() throws EmptyPriorityQueueException {
        if (L.isEmpty()) {
            throw new EmptyPriorityQueueException("优先队列空");
        }
        Position minPos = L.first();
        Iterator it = L.positions();
        while (it.hasNext()) {
            Position curPos = (Position) it.getNext();
            if (0 < C.compare(((Entry) minPos.getElement()).getKey(),
                    ((Entry) curPos.getElement()).getKey())) {
                minPos = curPos;
            }
        }
        return (Entry) L.remove(minPos);
    }
}
