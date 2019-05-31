package exceptions;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-29
 * time        : 16:54
 * description : 当试图使用非法关键码时，本异常将被抛出。
 */
public class InvalidKeyException extends RuntimeException {
    public InvalidKeyException(String error) {
        super(error);
    }
}
