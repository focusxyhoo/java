package collections;

import exceptions.StackEmptyException;

/**
 * 自定义实现一个栈，无关具体的实现方式。
 *
 * @author focusxyhoo
 * @date 2019-05-17 16:55
 */
public interface Stack {
    int getSize();

    boolean isEmpty();

    void push(Object element);

    // 出栈的操作都有可能出现栈空的情况。
    Object pop() throws StackEmptyException;

    Object top() throws StackEmptyException;

}
