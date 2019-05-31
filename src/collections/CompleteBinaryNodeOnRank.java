package collections;

/**
 * 基于秩实现的完全二叉树节点
 * @author focusxyhoo
 * @date 2019-05-26 12:59
 */
public class CompleteBinaryNodeOnRank extends BinaryTreeNode implements BinaryTreePosition {

    private Vector T;
    private int rank;
    private Object element;

    /******************************* 构造方法 **************************************/
    public CompleteBinaryNodeOnRank(Vector t, Object object) {
        element = object;
        T = t;
        rank = T.getSize();
        T.insertAtRank(rank, this);
    }

    // 返回当前节点中存放的对象
    public Object getElement() {
        return element;
    }

    // 将对象 object 存入当前节点，并返回此前的内容
    public Object setElement(Object object) {
        Object o = element;
        element = object;
        return o;
    }

    // 返回当前节点的父节点
    public BinaryTreePosition getParent() {
        return hasParent() ? (BinaryTreePosition) T.getAtRank((rank - 1) / 2) : null;
    }

    public boolean hasLChild() {
        return (1 + rank * 2 < T.getSize());
    }

    public BinaryTreePosition getLChild() {
        return hasLChild() ? (BinaryTreePosition) T.getAtRank(1 + rank * 2) : null;
    }

    public boolean hasRChild() {
        return 2 + rank * 2 < T.getSize();
    }

    public BinaryTreePosition getRChild() {
        return hasRChild() ? (BinaryTreePosition) T.getAtRank(2 + rank * 2) : null;

    }

    // 返回当前节点后代元素的数目
    public int getSize() {
        int size = 1;
        if (hasLChild()) size += getLChild().getSize();
        if (hasRChild()) size += getRChild().getSize();
        return size;
    }

    // 返回当前节点的高度
    public int getHeight() {
        int hL = hasLChild() ? getLChild().getHeight() : -1;
        int hR = hasRChild() ? getRChild().getHeight() : -1;
        return 1 + Math.max(hL, hR);
    }

    // 返回当前节点的深度
    public int getDepth() {
        return hasParent() ? 1 + getParent().getDepth() : 0;
    }
}
