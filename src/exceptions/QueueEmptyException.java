package exceptions;

/**
 * @author focusxyhoo
 * @date 2019-05-17 20:51
 */
public class QueueEmptyException extends RuntimeException {

    public QueueEmptyException(String error) {
        super(error);
    }
}
