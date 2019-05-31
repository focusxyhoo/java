package collections;

import exceptions.EmptyPriorityQueueException;
import exceptions.InvalidKeyException;

import java.time.Period;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-30
 * time        : 11:24
 * description : 基于列表（有序或无序）的方式实现的优先队列，其效率总是不尽如意。
 * 这是因为其在任何时候都保存了整个集合的全序关系，而实际上，优先队列只需要我们随时给出全局最小条目（或最大条目）即可。
 * 因此可以采用最小堆（最大堆）来实现优先队列。
 * 其查询操作时间复杂度为 O(1)，而插入和删除操作都可以在 O(logn) 的时间内完成。
 */
public class PriorityQueueOnHeap implements PriorityQueue {

    // 堆 H 是一棵完全二叉树，其中各节点存有条目，并根据其关键码满足堆序性
    private CompleteBinaryTree H;
    private Comparator comp;

    /******************************* 构造方法 **************************************/

    public PriorityQueueOnHeap() {
        this(new DefaultComparator(), null);
    }

    public PriorityQueueOnHeap(Comparator c) {
        this(c, null);
    }

    public PriorityQueueOnHeap(Sequence s) {
        this(new DefaultComparator(), s);
    }

    public PriorityQueueOnHeap(Comparator c, Sequence s) {
        comp = c;
        H = new CompleteBinaryTreeOnVector(s);
        if (!H.isEmpty()) {
            for (int i = H.getSize() / 2 - 1; i >= 0; i--) { // 自底向上
                percolateDown(H.poseOfNode(i)); // 逐节点进行下滤
            }
        }
    }

    /******************************* PriorityQueue 接口方法 **************************************/
    @Override
    public int getSize() {
        return H.getSize();
    }

    @Override
    public boolean isEmpty() {
        return H.isEmpty();
    }

    @Override
    public Entry getMin() throws EmptyPriorityQueueException {
        if (H.isEmpty()) throw new EmptyPriorityQueueException("优先队列空");
        return (Entry) H.getRoot().getElement();
    }

    /**
     * 插入条目。
     * 首先判断 key 的可比较性，然后将 key，obj包装为一个 Entry 对象，将 entry 添加到 h 的尾端，
     * 然后开始从尾端向上调整。
     *
     * @param key
     * @param obj
     * @return
     * @throws InvalidKeyException
     */
    @Override
    public Entry insert(Object key, Object obj) throws InvalidKeyException {
        checkKey(key);
        Entry entry = new DefaultEntry(key, obj);
        percolateUp(H.addLast(entry));
        return entry;
    }

    /**
     * 删除优先队列中最小的元素。
     * 基于最小堆实现的优先队列，直接取其根节点即为最小的元素。接着需要调整最小堆。
     * 当最小堆只有一个元素时，直接删除该元素即可；
     * 否则，取出堆中最后一个元素作为新的根节点，然后从根节点开始向下调整。
     *
     * @return
     * @throws EmptyPriorityQueueException
     */
    @Override
    public Entry delMin() throws EmptyPriorityQueueException {
        if (isEmpty()) throw new EmptyPriorityQueueException("优先队列空");
        Entry min = (Entry) H.getRoot().getElement();
        if (1 == getSize()) {
            H.deleteLast();
        } else {
            H.getRoot().setElement(((CompleteBinaryNodeOnRank) H.deleteLast()).getElement());
            percolateDown(H.getRoot());
        }
        return min;
    }

    /******************************* 辅助方法 **************************************/

    /**
     * 检查关键码的可比较性
     *
     * @param key
     * @throws InvalidKeyException
     */
    protected void checkKey(Object key) throws InvalidKeyException {
        try {
            comp.compare(key, key);
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException("无法比较关键码");
        }
    }

    /**
     * 返回节点 v 的关键码
     *
     * @param v
     * @return
     */
    protected Object key(BinaryTreePosition v) {
        return ((Entry) v.getElement()).getKey();
    }

    /******************************* 算法方法 **************************************/

    /**
     * 交换父子节点。当父子节点不满足最小堆的规则时，需要进行该操作。
     *
     * @param p
     * @param c
     */
    protected void swapParentChild(BinaryTreePosition p, BinaryTreePosition c) {
        Object temp = p.getElement();
        p.setElement(c.getElement());
        c.setElement(temp);
    }

    /**
     * 上滤算法
     * 从当前节点 v 开始，依次与其父节点进行比较，当父节点大于 v 时，交换位置。重复进行直至根节点。
     *
     * @param v
     */
    protected void percolateUp(BinaryTreePosition v) {
        BinaryTreePosition root = H.getRoot();
        while (v != H.getRoot()) {
            BinaryTreePosition p = v.getParent();
            if (0 > comp.compare(key(p), key(v))) break; // 除非父亲比孩子小，否则交换父子次序
            swapParentChild(p, v);
            v = p;
        }
    }

    /**
     * 下滤算法
     * 从当前节点 v 开始，依次与其最小子节点进行比较，当其最小子节点小时，交换 v 与其最小子节点的位置。重复进行直至叶子节点。
     *
     * @param v
     */
    protected void percolateDown(BinaryTreePosition v) {
        while (v.hasLChild()) {
            BinaryTreePosition smallerChild = v.getLChild();
            if (v.hasRChild() && 0 < comp.compare(key(v.getLChild()), key(v.getRChild()))) {
                smallerChild = v.getRChild();
            }
            if (0 <= comp.compare(key(smallerChild), key(v))) {
                break;
            }
            swapParentChild(v, smallerChild);
            v = smallerChild;
        }
    }
}
