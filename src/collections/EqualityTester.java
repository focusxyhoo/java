package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-30
 * time        : 15:47
 * description : 判等器接口
 */
public interface EqualityTester {
    /**
     * 判断 a 与 b 是否相等。
     * 若相等，返回 true，否则返回 false。
     * @param a
     * @param b
     * @return
     */
    boolean isEqualTo(Object a, Object b);
}
