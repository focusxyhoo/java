package exceptions;

/**
 * 当栈空时，出栈或返回栈顶元素会报该异常。
 *
 * @author focusxyhoo
 * @date 2019-05-17 16:54
 */
public class StackEmptyException extends RuntimeException {
    public StackEmptyException(String error) {
        super(error);
    }
}
