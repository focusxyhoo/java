package exceptions;

/**
 * 当列表长度为空时，返回列表元素的操作都会触发本异常。
 *
 * @author focusxyhoo
 * @date 2019-05-23 20:30
 */
public class ListEmptyException extends RuntimeException {
    public ListEmptyException(String error) {
        super(error);
    }
}
