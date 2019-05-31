package exceptions;

/**
 * 序列位置越界时，将报出本异常。
 * @author focusxyhoo
 * @date 2019-05-23 16:57
 */
public class BoundaryViolationException extends RuntimeException {
    public BoundaryViolationException(String error) {
        super(error);
    }
}
