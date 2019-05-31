package exceptions;

/**
 * 当在序列中查找不到指定元素时会报该异常。
 *
 * @author focusxyhoo
 * @date 2019-05-23 21:47
 */
public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException(String error) {
        super(error);
    }
}
