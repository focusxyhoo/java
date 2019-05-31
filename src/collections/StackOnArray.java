package collections;

import exceptions.StackEmptyException;
import exceptions.StackFullException;

/**
 * 采用数组的方式来实现栈。
 * 复杂度分析：所有方法的时间复杂度都是 O(1)，空间复杂度为 O(N)。
 * 缺点：内部数组的容量是固定的，因而很难适应实际需要。
 * 对象类型：内部数组存储对象是 Object 类型，因而可以存放任何类型的数据，但是在取出元素时要进行类型强制转换。
 * 应用：数组倒置。
 *
 * @author focusxyhoo
 * @date 2019-05-17 17:02
 */
public class StackOnArray implements Stack {

    // 数组默认容量。
    public static final int CAPACITY = 1024;
    // 数组实际容量。
    protected int capacity;
    // 对象数组。
    protected Object[] stack;
    // 记录栈顶元素的位置。
    protected int top = -1;

    // 按照默认容量创建栈对象。
    public StackOnArray() {
        this(CAPACITY);
    }

    // 根据自定义容量创建栈对象。
    public StackOnArray(int capacity) {
        this.capacity = capacity;
        this.stack = new Object[capacity];
    }

    // 获取栈的大小。
    @Override
    public int getSize() {
        return (top + 1);
    }

    // 测试栈是否为空。
    @Override
    public boolean isEmpty() {
        return (top < 0);
    }

    // 入栈。
    @Override
    public void push(Object element) throws StackFullException {
        if (capacity == getSize()) {
            throw new StackFullException("栈溢出！");
        }
        stack[++top] = element;
    }

    // 出栈。
    @Override
    public Object pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("栈空");
        }
        Object elem = stack[top];
        // 这一步是为了垃圾回收机制可以回收原栈顶元素对象。
        stack[top--] = null;
        return elem;
    }

    // 取栈顶元素。
    @Override
    public Object top() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("栈空");
        }
        return stack[top];
    }
}
