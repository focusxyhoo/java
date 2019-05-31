package collections;

/**
 * 基于链表实现的树结构。
 *
 * @author focusxyhoo
 * @date 2019-05-24 15:22
 */
public class TreeLinkedList implements Tree {

    private Object object;
    private TreeLinkedList parent, firstChild, nextSibling;

    public TreeLinkedList() {
        this(null, null, null, null);
    }

    // 构造方法
    public TreeLinkedList(Object object, TreeLinkedList parent, TreeLinkedList firstChild, TreeLinkedList nextSibling) {
        this.object = object;
        this.parent = parent;
        this.firstChild = firstChild;
        this.nextSibling = nextSibling;
    }

    /* ------------------------Tree 接口中各方法的实现--------------------------- */

    @Override
    public Object getElement() {
        return object;
    }

    @Override
    public Object setElement(Object object) {
        Object o = this.object;
        this.object = object;
        return o;
    }

    @Override
    public TreeLinkedList getParent() {
        return parent;
    }

    @Override
    public TreeLinkedList getFirstChild() {
        return firstChild;
    }

    @Override
    public TreeLinkedList getNextSibling() {
        return nextSibling;
    }

    @Override
    public int getSize() {
        // 注意：当前节点也需要算进去。
        int size = 1;
        TreeLinkedList subTree = firstChild;
        while (null != subTree) {
            size += subTree.getSize();
            subTree = subTree.getNextSibling();
        }
        return size;
    }

    @Override
    public int getHeight() {
        // 高度是从当前节点往下数。
        int height = -1;
        TreeLinkedList subTree = firstChild;
        while (null != subTree) {
            height = Math.max(height, subTree.getHeight());
            subTree = subTree.getNextSibling();
        }
        return height + 1;
    }

    @Override
    public int getDepth() {
        // 深度是从父节点开始往上数。
        int depth = 0;
        TreeLinkedList p = parent;
        while (null != p) {
            depth++;
            p = p.getParent();
        }
        return depth;
    }
}
