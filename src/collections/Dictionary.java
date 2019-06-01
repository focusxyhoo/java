package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-31
 * time        : 14:59
 * description : 无序词典结构接口
 * 词典与映射之间的区别在于：词典中不再要求各条目的关键码互异。
 * 同样的，当因为查找到某一指定关键码的条目而导致查找失败时，直接返回 null 会导致歧义。
 */
public interface Dictionary {

    int getSize();

    boolean isEmpty();

    // 若词典中有以 key 为关键码的条目，返回其中一个，否则返回 null
    Entry find(Object key);

    // 返回词典中所有以 key 为关键码的条目组成的迭代器
    Iterator findAll(Object key);

    // 插入条目 key，value 对并返回
    Entry insert(Object key, Object value);

    // 若此单中有以 key 为关键码的条目，删除其中一个并返回
    Entry remove(Object key);

    // 返回由词典中所有条目组成的迭代器
    Iterator entries();
}
