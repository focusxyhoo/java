package exceptions;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-29
 * time        : 16:56
 * description : 当试图对空的优先队列应用 getMin 或 delMin方法时，本异常将被抛出。
 */
public class EmptyPriorityQueueException extends RuntimeException {
    public EmptyPriorityQueueException(String message) {
        super(message);
    }
}
