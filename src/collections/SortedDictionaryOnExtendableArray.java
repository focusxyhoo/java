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

    // binary search
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
        return null;
    }

    @Override
    public Entry insert(Object key, Object value) {
        return null;
    }

    @Override
    public Entry remove(Object key) {
        return null;
    }

    @Override
    public Iterator entries() {
        return null;
    }

    @Override
    public Entry first() {
        return null;
    }

    @Override
    public Entry last() {
        return null;
    }

    @Override
    public Iterator successors(Object key) {
        return null;
    }

    @Override
    public Iterator predecessors(Object key) {
        return null;
    }
}
