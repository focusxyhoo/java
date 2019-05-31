package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-30
 * time        : 15:39
 * description : 映射结构接口
 * 注意：在 get、put、remove 方法中，当映射中不存在指定的条目时，直接返回 null，而非采用报错的方式。
 * 这是因为在实际应用中，这种情况会经常出现，采用报错的方式会导致效率低下。
 * 但这种处理方式也会导致问题：调用者无法知道究竟是映射结构中不存在该条目，还是的确存在以 null 为关键码的条目。
 */
public interface Map {

    int getSize();

    boolean isEmpty();

    Object get(Object key);

    Object put(Object key, Object value);

    Object remove(Object key);

    Iterator entries();
}
