package collections;

/**
 * 基于位置接口实现的双向链表节点类。
 * 保证无论从链表哪一端删除节点，时间复杂度都是 O(1)。
 *
 * @author focusxyhoo
 * @date 2019-05-23 15:52
 */
public class DLNode implements Position {

    private Object element;
    private DLNode prev;
    private DLNode next;

    public DLNode() {
        this(null, null, null);
    }

    public DLNode(Object element, DLNode prev, DLNode next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public Object getElement() {
        return element;
    }

    @Override
    public Object setElement(Object e) {
        Object o = element;
        element = e;
        return o;
    }

    public DLNode getPrev() {
        return prev;
    }

    public void setPrev(DLNode prev) {
        this.prev = prev;
    }

    public DLNode getNext() {
        return next;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }
}
