package collections;

/**
 * 实现单链表节点类。
 * 链表相对数组来说，没有大小限制，
 *
 * @author focusxyhoo
 * @date 2019-05-17 19:29
 */
public class Node {

    private Object element;

    private Node next;

    // 构造函数
    public Node() {
        this(null, null);
    }

    public Node(Object element, Node next) {
        this.element = element;
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    // 将给定元素存放至该位置，范围之前的元素。
    public Object setElement(Object element) {
        Object e = this.element;
        this.element = element;
        return e;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
