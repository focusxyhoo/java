package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-30
 * time        : 15:49
 * description : 默认的判等器类。
 */
public class DefaultEqualityTester implements EqualityTester {

    public DefaultEqualityTester() {
    }

    @Override
    public boolean isEqualTo(Object a, Object b) {
        // 使用 Java 提供的判等器
        return a.equals(b);
    }

}
