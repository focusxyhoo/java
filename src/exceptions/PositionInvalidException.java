package exceptions;

/**
 * 当作为参数的数组下标、向量的秩或列表的位置非法时，本意外将被抛出。
 *
 * @author focusxyhoo
 * @date 2019-05-23 20:04
 */
public class PositionInvalidException extends RuntimeException {
    public PositionInvalidException(String error) {
        super(error);
    }
}
