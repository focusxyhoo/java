package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-29
 * time        : 16:42
 * description : 比较器接口。
 *               实现了比较器接口的类，可以确定一个具体的比较规则。
 *               这样需要进行对象比较的类可以直接传入该比较器对象进行规则比较，且可以随时通过更换比较器对象来重新选择比较规则。
 */
public interface Comparator {
    /**
     * 若 a > b，返回正数；
     * 若 a = b，返回零；
     * 若 a < b，返回负数。
     *
     * @param a
     * @param b
     * @return
     */
    int compare(Object a, Object b);
}
