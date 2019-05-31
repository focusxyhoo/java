package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-30
 * time        : 17:00
 * description : 基于散列表实现的映射结构。采用分离链策略解决冲突。
 */
public class MapOnHashTable implements Map {

    private Map[] A;
    private int N;
    private final double maxCapality = 0.75;
    private int size;
    private EqualityTester T;


    /******************************* 构造方法 **************************************/

    public MapOnHashTable() {
        this(0, new DefaultEqualityTester());
    }

    public MapOnHashTable(int n, EqualityTester t) {
        T = t;
        N = p(n);
        A = new Map[N];
        for (int i = 0; i < N; i++) {
            A[i] = new MapOnDLNode(T);
        }
        size = 0;
    }


    /******************************* Map 接口方法 **************************************/
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object get(Object key) {
        return A[h(key)].get(key);
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Object put(Object key, Object value) {
        Object oldValue = A[h(key)].put(key, value);
        if (null == oldValue) {
            size++;
            if (size > N * maxCapality) {
                reharsh();
            }
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        Object oldValue = A[h(key)].remove(key);
        if (null != oldValue) {
            size--;
        }
        return oldValue;
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

    /******************************* 辅助方法 **************************************/

    /**
     * 散列定址函数。
     *
     * @param key
     * @return
     */
    private int h(Object key) {
        return key.hashCode() % N;
    }

    /**
     * 判断 n 是否是素数。
     *
     * @param n
     * @return
     */
    private static boolean prime(int n) {
        for (int i = 3; i < 1 + Math.sqrt(n); i++) {
            if (n / i * i == n) {
                return false;
            }
        }
        return true;
    }

    /**
     * 取不小于 n 的最小素数。
     *
     * @param n
     * @return
     */
    private static int p(int n) {
        if (3 > n) {
            n = 3;
        }
        n = n | 1;
        while (!prime(n)) {
            n += 2;
        }
        return n;
    }

    /**
     * 重散列。
     */
    private void reharsh() {
        Iterator it = this.entries();
        N = p(N << 1);
        A = new Map[N];
        for (int i = 0; i < N; i++) {
            A[i]=new MapOnDLNode(T);
        }
        while (it.hasNext()) {
            Entry e = (Entry) it.getNext();
            Object k = e.getKey();
            Object v = e.getValue();
            A[h(k)].put(k, v);
        }
    }
}
