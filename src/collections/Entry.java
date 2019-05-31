package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-29
 * time        : 16:32
 * description : 所谓的条目，即是将关键码和数据对象打包组合成一个大的复合对象，这种技巧称为合成模式（Composition pattern）。
 *               关键码用来比较两个数据对象的大小关系。
 */
public interface Entry {

    // 取条目的关键码
    Object getKey();

    // 修改条目的关键码，返回此前存放的关键码
    Object setKey(Object k);

    // 取条目的数据对象
    Object getValue();

    // 修改条目的数据对象，返回此前存放的数据对象
    Object setValue(Object v);
}
