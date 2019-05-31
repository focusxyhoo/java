package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-30
 * time        : 15:54
 * description : 基于列表实现的映射结构。
 * 效率不高。
 * get、put、remove 方法都需要扫描全表，时间复杂度为 O(n)。
 */
public class MapOnDLNode implements Map {

    private List L;
    private EqualityTester T;

    /******************************* 构造方法 **************************************/

    public MapOnDLNode() {
        this(new DefaultEqualityTester());
    }

    public MapOnDLNode(EqualityTester tester) {
        L = new ListOnDLNode();
        T = tester;
    }

    /******************************* Map 接口方法 **************************************/

    @Override
    public int getSize() {
        return L.getSize();
    }

    @Override
    public boolean isEmpty() {
        return L.isEmpty();
    }

    /**
     * 返回 Map 中关键码为 key 的条目中的数据对象。
     * 若不存在，返回null。
     *
     * @param key
     * @return
     */
    @Override
    public Object get(Object key) {
        Iterator it = L.positions();
        while (it.hasNext()) {
            Position p = (Position) it.getNext();
            Entry entry = (DefaultEntry) p.getElement();
            if (T.isEqualTo(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 向 Map 中插入新的 (key,value) 键值对。
     * 遍历 Map 结构，若 key 存在，则用新的 value 值替换旧值，并返回旧值。
     * 若不存在，则直接插入，并返回 null。
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Object put(Object key, Object value) {
        Iterator it = L.positions();
        while (it.hasNext()) {
            Position p = (Position) it.getNext();
            Entry entry = (DefaultEntry) p.getElement();
            if (T.isEqualTo(entry.getKey(), key)) {
                Object oldValue = entry.getValue();
                L.replace(p, new DefaultEntry(key, value));
                return oldValue;
            }
        }
        L.insertFirst(new DefaultEntry(key, value));
        return null;
    }

    /**
     * 从 Map 结构中删除以 key 为关键码的条目。
     * 若存在该条目，删除并返回其数据对象。
     * 若不存在，返回 null。
     *
     * @param key
     * @return
     */
    @Override
    public Object remove(Object key) {
        Iterator it = L.positions();
        while (it.hasNext()) {
            Position p = (Position) it.getNext();
            Entry entry = (DefaultEntry) p.getElement();
            if (T.isEqualTo(entry.getKey(), key)) {
                Object oldValue = entry.getValue();
                L.remove(p);
                return oldValue;
            }
        }
        return null;
    }

    /**
     * 返回 Map 中所有条目的一个迭代器。
     * 直接利用 list 接口的方法来生成元素迭代器。
     *
     * @return
     */
    @Override
    public Iterator entries() {
        return new IteratorOnElement(L);
    }
}
