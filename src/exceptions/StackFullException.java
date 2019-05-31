package exceptions;

/**
 * 当栈满时，入栈操作会报该异常。
 * 仅针对数组实现的栈。
 *
 * @author focusxyhoo
 * @date 2019-05-17 17:00
 */
public class StackFullException extends RuntimeException {
    public StackFullException(String error) {
        super(error);
    }
}
