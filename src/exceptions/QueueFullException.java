package exceptions;

/**
 * @author focusxyhoo
 * @date 2019-05-23 14:48
 */
public class QueueFullException extends RuntimeException {

    public QueueFullException(String error) {
        super(error);
    }
}
