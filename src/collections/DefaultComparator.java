package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-29
 * time        : 16:46
 * description : Comparable 对象的默认比较器
 */
public class DefaultComparator implements Comparator {

    public DefaultComparator() {}

    @Override
    public int compare(Object a, Object b) throws ClassCastException{
        return ((Comparable) a).compareTo(b);
    }
}
