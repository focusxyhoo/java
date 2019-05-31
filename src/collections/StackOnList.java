package collections;

import exceptions.StackEmptyException;

/**
 * 基于单链表的栈的实现。
 * 栈的存储空间完全基于实际保存的节点个数，而不是一成不变。
 * 相对利用数组的实现方式，无需单独设置一个溢出问题的异常。
 * 在这里，设置了一个实例变量top，指向表中的首节点，所有的操作的时间复杂度都是 O(1)。
 * 头插法。
 *
 * @author focusxyhoo
 * @date 2019-05-17 19:25
 */
public class StackOnList implements Stack {

    private int size;

    private Node top;

    public StackOnList() {
        this.size = 0;
        this.top = null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (top == null) ? true : false;
    }

    @Override
    public void push(Object element) {
        Node v = new Node(element, top);
        top = v;
        size++;
    }

    @Override
    public Object pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("栈空！");
        }
        Object e = top.getElement();
        top = top.getNext();
        size--;
        return e;
    }

    @Override
    public Object top() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("栈空！");
        }
        return top.getElement();
    }
}
