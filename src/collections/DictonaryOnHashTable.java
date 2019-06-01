package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-31
 * time        : 15:34
 * description : 基于散列表实现的无序词典结构。
 * 采用分离链策略解决冲突。
 */
public class DictonaryOnHashTable implements Dictionary {

    private Dictionary[] A;
    private int N;
    private final double maxLemda = 0.75; //装填因子上限
    private int size;
    private EqualityTester T;

    /******************************* 构造方法 **************************************/

    public DictonaryOnHashTable() {
        this(0, new DefaultEqualityTester());
    }

    public DictonaryOnHashTable(int n, EqualityTester t) {
        T = t;
        N = MapOnHashTable.p(N);
        A = new Dictionary[N];
        for (int i = 0; i < N; i++) {
            A[i] = new DictionaryONDLNode(T);
        }
        size = 0;
    }


    /******************************* 辅助方法 **************************************/

    private int h(Object key) {
        return key.hashCode() % N;
    }

    /**
     * 重散列。
     */
    private void reharsh() {
        Iterator it = this.entries();
        N = MapOnHashTable.p(N << 1);
        A = new Dictionary[N];
        for (int i = 0; i < N; i++) {
            A[i]=new DictionaryONDLNode(T);
        }
        while (it.hasNext()) {
            Entry e = (Entry) it.getNext();
            Object k = e.getKey();
            Object v = e.getValue();
            A[h(k)].insert(k, v);
        }
    }

    /******************************* Dictionary 接口方法 **************************************/
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    @Override
    public Entry find(Object key) {
        return A[h(key)].find(key);
    }

    @Override
    public Iterator findAll(Object key) {
        return A[h(key)].findAll(key);
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry entry = A[h(key)].insert(key, value);
        size++;
        if (size > N * maxLemda) {
            reharsh();
        }
        return entry;
    }

    @Override
    public Entry remove(Object key) {
        Entry oldEntry = A[h(key)].remove(key);
        if (null != oldEntry) {
            size--;
        }
        return oldEntry;
    }

    @Override
    public Iterator entries() {
        List L = new ListOnDLNode();
        for (int i = 0; i < N; i++) {
            Iterator it = A[i].entries();
            while (it.hasNext()) {
                L.insertLast(it.getNext());
            }
        }
        return new IteratorOnElement(L);
    }
}
