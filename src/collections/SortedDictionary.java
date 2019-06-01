package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-06-01
 * time        : 12:41
 * description : 有序词典接口。
 */
public interface SortedDictionary extends Dictionary {

    // 若词典非空，返回其中关键码最小的条目，否则返回 null。
    Entry first();

    // 若词典非空，返回其中关键码最大的条目，否则返回 null。
    Entry last();

    // 返回由关键码不小于 key 的条目依非降序组成的迭代器。
    Iterator successors(Object key);

    // 返回由关键码不大于 key 的条目依飞升序组成的迭代器。
    Iterator predecessors(Object key);
}
