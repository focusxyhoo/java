package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-31
 * time        : 15:07
 * description : 基于列表实现的无序词典结构。
 * 性能分析：空间复杂度为 O(n)。
 * 可以看到，查找和删除都需要通过迭代器扫描整个列表，其效率非常低，最坏情况下时间复杂度为 O(n)。
 */
public class DictionaryONDLNode implements Dictionary {

    private List L;
    private EqualityTester T;

    /******************************* 构造方法 **************************************/
    public DictionaryONDLNode() {
        this(new DefaultEqualityTester());
    }

    public DictionaryONDLNode(EqualityTester t) {
        L = new ListOnDLNode();
        T = t;
    }

    /******************************* Dictionary 接口方法 **************************************/
    @Override
    public int getSize() {
        return L.getSize();
    }

    @Override
    public boolean isEmpty() {
        return L.isEmpty();
    }

    /**
     * 若词典中存在以 key 为关键码的条目，则返回其中的一个条目。
     * 否则返回 null。
     *
     * @param key
     * @return
     */
    @Override
    public Entry find(Object key) {
        Iterator it = L.positions();
        while (it.hasNext()) {
            Position pos = (Position) it.getNext();
            Entry entry = (Entry) pos.getElement();
            if (T.isEqualTo(entry.getKey(), key)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * 返回所有以 key 为关键码的条目组成的迭代器。
     *
     * @param key
     * @return
     */
    @Override
    public Iterator findAll(Object key) {
        List list = new ListOnDLNode();
        Iterator it = L.positions();
        while (it.hasNext()) {
            Position pos = (Position) it.getNext();
            Entry entry = (Entry) pos.getElement();
            if (T.isEqualTo(entry.getKey(), key)) {
                list.insertLast(entry);
            }
        }
        return new IteratorOnElement(list);
    }

    /**
     * 插入条目 key，value 并返回。
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Entry insert(Object key, Object value) {
        Entry entry = new DefaultEntry(key, value);
        L.insertFirst(entry);
        return entry;
    }

    /**
     * 若词典中存在以 key 为关键码的条目，删除其中的一个条目并返回。
     * 否则返回 null。
     * 若需要删除多个或所有，依次调用即可。
     *
     * @param key
     * @return
     */
    @Override
    public Entry remove(Object key) {
        Iterator it = L.positions();
        while (it.hasNext()) {
            Position pos = (Position) it.getNext();
            Entry entry = (Entry) pos.getElement();
            if (T.isEqualTo(entry.getKey(), key)) {
                Entry oldValue = entry;
                L.remove(pos);
                return oldValue;
            }
        }
        return null;
    }

    /**
     * 返回词典中所有条目的一个迭代器。
     *
     * @return
     */
    @Override
    public Iterator entries() {
        return new IteratorOnElement(L);
    }
}
