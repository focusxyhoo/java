package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-06-01
 * time        : 12:47
 * description : the implementation of sorted dictionary based on ordered search.
 */
public class SortedDictionaryOnExtendableArray implements SortedDictionary {

    Vector S;
    Comparator C;

    // default constructor
    public SortedDictionaryOnExtendableArray() {
        this(new DefaultComparator());
    }

    // constructor
    public SortedDictionaryOnExtendableArray(Comparator comp) {
        S = new VectorOnExtendableArray();
        C = comp;
    }

    /**
     * 二分查找的写法
     * @param s 序列
     * @param c 比较器
     * @param key 待查找的关键码
     * @param low 最低指标
     * @param high 最大指标
     * @return
     */
    private static int binSearch(Vector s, Comparator c, Object key, int low, int high) {
        if (low > high) return low;
        int middle = (low + high) >> 1;
        Entry entry = (Entry) s.getAtRank(middle);
        int flag = c.compare(key, entry.getKey());
        if (flag < 0) {
            return binSearch(s, c, key, low, middle - 1);
        } else if (flag > 0) {
            return binSearch(s, c, key, middle + 1, high);
        } else return middle;
    }

    @Override
    public int getSize() {
        return S.getSize();
    }

    @Override
    public boolean isEmpty() {
        return S.isEmpty();
    }

    @Override
    public Entry find(Object key) {
        // find the rank by binary search
        int k = binSearch(S, C, key, 0, S.getSize() - 1);
        // if not exist, return null
        if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))) {
            return null;
        }
        return (Entry) S.getAtRank(k);
    }

    @Override
    public Iterator findAll(Object key) {
        List L = new ListOnDLNode();

        int k = binSearch(S, C, key, 0, S.getSize() - 1);
        if (0 > k || k > S.getSize() || 0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))
            return new IteratorOnElement(L);

        L.insertFirst(S.getAtRank(k)); // 将 e 插入 L 中

        int low = k; // 从 S[k-1]开始向前扫描
        while (0 <= --low) {
            if (0 != C.compare(key, ((Entry) S.getAtRank(low)).getKey())) break;
            L.insertFirst(S.getAtRank(low));
        }
        int high = k; // 从 S[k+1]开始向后扫描
        while (++high < S.getSize()) {
            if (0 != C.compare(key, ((Entry) S.getAtRank(high)).getKey())) break;
            L.insertFirst(S.getAtRank(high));
        }

        return new IteratorOnElement(L);
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry entry = new DefaultEntry(key, value);

        if (S.isEmpty()) return (Entry) S.insertAtRank(0, entry);

        // 通过二分查找确定插入位置
        return (Entry) S.insertAtRank(binSearch(S, C, key, 0, S.getSize() - 1), entry);
    }

    @Override
    public Entry remove(Object key) {
        int k = binSearch(S, C, key, 0, S.getSize() - 1);
        // 若不存在，返回失败
        if (0 > k || k >= S.getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey())))
            return null;
        return (Entry) S.removeAtRank(k);
    }

    @Override
    public Iterator entries() {
        List L = new ListOnDLNode();
        for (int i = 0; i < S.getSize(); i++)
            L.insertLast(S.getAtRank(i));
        return new IteratorOnElement(L);
    }

    @Override
    public Entry first() {
        return S.isEmpty() ? null : (Entry) S.getAtRank(0);
    }

    @Override
    public Entry last() {
        return S.isEmpty() ? null : (Entry) S.getAtRank(S.getSize() - 1);
    }

    /**
     * 返回由关键码不小于 key 的条目依非升序组成的迭代器
     * @param key
     * @return
     */
    @Override
    public Iterator successors(Object key) {
        List L = new ListOnDLNode();

        int k = binSearch(S, C, key, 0, S.getSize() - 1);
        if (0 > k || k >= S.getSize() || 0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))
            return new IteratorOnElement(L);

        // 由二分查找确定 key 在字典中的位置后，再继续向前搜索，直至找到复合要求的秩最小的条目
        while (0 <= --k)
            if (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey())) break;
        // 然后将 k 后面的条目全部添加到链表中
        while (S.getSize() > ++k)
            L.insertLast(S.getAtRank(k));
        return new IteratorOnElement(L);
    }

    /**
     * 返回由关键码不大于 key 的条目依非升序组成的迭代器
     * @param key
     * @return
     */
    @Override
    public Iterator predecessors(Object key) {
        List L = new ListOnDLNode();

        int k = binSearch(S, C, key, 0, S.getSize() - 1);
        if (0 > k || k >= S.getSize() || 0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))
            return new IteratorOnElement(L);

        while (S.getSize()>++k)
            if (0!=C.compare(key, ((Entry)S.getAtRank(k)).getKey())) break;
        while (0<=--k)
            L.insertLast(S.getAtRank(k));
        return new IteratorOnElement(L);
    }
}
