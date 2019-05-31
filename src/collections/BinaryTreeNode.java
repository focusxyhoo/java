package collections;

/**
 * 基于链表节点实现二叉树节点。
 *
 * @author focusxyhoo
 * @date 2019-05-24 16:21
 */
public class BinaryTreeNode implements BinaryTreePosition {
    protected Object element;
    protected BinaryTreePosition parent;
    protected BinaryTreePosition lChild;
    protected BinaryTreePosition rChild;
    protected int size; // 后代数目
    protected int height;
    protected int depth;

    /******************************* Object 方法 **************************************/
    // 注意一定要覆盖 toString 方法，否则输出时得到的不是想要的结果。
    @Override
    public String toString() {
        return element.toString();
    }

    /******************************* 构造方法 **************************************/

    public BinaryTreeNode() {
        this(null, null, true, null, null);
    }

    /**
     * 构造方法
     * @param e 节点内容
     * @param p 父节点
     * @param asLChild 是否作为父节点的左孩子节点
     * @param l 左孩子节点
     * @param r 右孩子节点
     */
    public BinaryTreeNode(Object e, BinaryTreePosition p, boolean asLChild, BinaryTreePosition l, BinaryTreePosition r) {
        element = e;
        // 初始化
        size = 1;
        height = depth = 0;
        parent = lChild = rChild = null;

        // 建立与父节点的关系
        if (null != p) {
            if (asLChild) p.attachL(this);
            else p.attchR(this);
        }

        // 建立与子节点的关系
        if (null != l) attachL(l);
        if (null != r) attchR(r);
    }

    /******************************* BinaryTreePosition 接口方法 **************************************/

    @Override
    public boolean hasParent() {
        return null != parent;
    }

    @Override
    public BinaryTreePosition getParent() {
        return parent;
    }

    @Override
    public void setParent(BinaryTreePosition parent) {
        this.parent = parent;
    }

    @Override
    public boolean isLeaf() {
        return !hasLChild() && !hasParent();
    }

    @Override
    public boolean isLChild() {
        // 首先要先有父节点，然后判断父节点的左节点是否是本身
        return (hasParent() && this == getParent().getLChild());
    }

    @Override
    public boolean hasLChild() {
        return null != lChild;
    }

    @Override
    public BinaryTreePosition getLChild() {
        return lChild;
    }

    @Override
    public void setLChild(BinaryTreePosition lChild) {
        this.lChild = lChild;
    }

    @Override
    public boolean isRChild() {
        return hasParent() && this == getParent().getRChild();
    }

    @Override
    public boolean hasRChild() {
        return null != rChild;
    }

    @Override
    public BinaryTreePosition getRChild() {
        return rChild;
    }

    @Override
    public void setRChild(BinaryTreePosition rChild) {
        this.rChild = rChild;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void updateSize() {
        size = 1;
        if (hasLChild()) size += getLChild().getSize();
        if (hasRChild()) size += getRChild().getSize();

        if (hasParent()) getParent().updateSize(); // 递归更新各个真祖先的规模记录
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void updateHeight() {
        height = 0; // 先假设没有左、右孩子节点
        if (hasLChild()) height = Math.max(height, 1 + getLChild().getHeight());
        if (hasRChild()) height = Math.max(height, 1 + getRChild().getHeight());

        if (hasParent()) getParent().updateHeight(); // 递归更新各个真祖先的高度记录
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void updateDepth() {
        depth = hasParent() ? 1 + getParent().getDepth() : 0;
        // 递归地更新所有后代的深度记录
        if (hasLChild()) getLChild().updateHeight();
        if (hasRChild()) getRChild().updateHeight();
    }

    @Override
    public BinaryTreePosition getPrev() {
        // 先分析简单情况
        // 若左子树非空，则其中的最大者即为当前节点的直接前驱
        if (hasLChild()) return findMaxDescendant(getLChild());
        // 至此，当前下姜末没有左孩子。
        // 若当前节点是右孩子，则其父节点为直接前驱
        if (isRChild()) return getParent();
        // 至此，当前节点没有左孩子，且是左孩子
        // 从当前节点出发
        BinaryTreePosition v = this;
        // 沿着左孩子链一直上升
        while (v.isLChild()) v = v.getParent();
        // 至此，v 或者没有父节点，或者是父节点的右孩子
        return v.getParent();
    }

    @Override
    public BinaryTreePosition getSucc() {
        if (hasRChild()) return findMinDescendant(getRChild());
        if (isLChild()) return getParent();
        BinaryTreePosition v = this;
        while (v.isRChild()) v = v.getParent();
        return v.getParent();
    }

    @Override
    public BinaryTreePosition secede() {
        if (null != parent) {
            // 切断父节点指向当前节点的引用，这将影响父节点及其祖先节点的规模与高度。
            if (isLChild()) parent.setLChild(null);
            else parent.setRChild(null);
            parent.updateSize();
            parent.updateHeight();

            // 切断当前节点指向父节点的引用，这只会影响当前节点 及其后代节点的深度。
            parent = null;
            updateDepth();
        }
        return null;
    }

    @Override
    public BinaryTreePosition attachL(BinaryTreePosition c) {
        // 如果有左孩子节点，那么先清除
        if (hasLChild()) getLChild().secede();
        if (null != c) {
            c.secede();
            lChild = c;
            c.setParent(this);
            // 更新当前节点及其祖先的规模和高度
            updateSize();
            updateHeight();
            // 更新 c 及其后代节点的深度
            updateDepth();
        }
        return null;
    }

    @Override
    public BinaryTreePosition attchR(BinaryTreePosition c) {
        if (hasRChild()) getRChild().secede();
        if (null != c) {
            c.secede();
            rChild = c;
            c.setParent(this);
            updateSize();
            updateHeight();
            updateDepth();
        }
        return this;
    }

    @Override
    public Iterator elementsPreorder() {
        List list = new ListOnDLNode();
        preorder(list, this);
        return list.elements();
    }

    @Override
    public Iterator elementsInorder() {
        List list = new ListOnDLNode();
        inorder(list, this);
        return list.elements();
    }

    @Override
    public Iterator elmentsPostorder() {
        List list = new ListOnDLNode();
        postorder(list, this);
        return list.elements();
    }

    @Override
    public Iterator elementsLevelorder() {
        List list = new ListOnDLNode();
        levelorder(list, this);
        return list.elements();
    }

    /******************************* Position 接口方法 **************************************/

    @Override
    public Object getElement() {
        return element;
    }

    @Override
    public Object setElement(Object element) {
        Object o = this.element;
        this.element = element;
        return o;
    }

    /******************************* 辅助方法 **************************************/

    // 在 v 的后代中，找出最小者
    protected static BinaryTreePosition findMinDescendant(BinaryTreePosition v) {
        if (null != v) {
            while (v.hasLChild()) v = v.getLChild();
        }
        return v;
    }

    // 在 v 的后代中，找出最小者
    protected static BinaryTreePosition findMaxDescendant(BinaryTreePosition v) {
        if (null != v) {
            while (v.hasRChild()) v = v.getRChild();
        }
        return v;
    }

    // 前序遍历以 v 为根节点的子树
    protected static void preorder(List list, BinaryTreePosition v) {
        if (null == v) return; // 递归基
        list.insertLast(v);
        preorder(list, v.getLChild());
        preorder(list, v.getRChild());
    }

    // 中序遍历以 v 为根节点的子树
    protected static void inorder(List list, BinaryTreePosition v) {
        if (null == v) return;
        inorder(list, v.getLChild());
        list.insertLast(v);
        inorder(list, v.getRChild());
    }

    // 后序遍历以 v 为根节点的子树
    protected static void postorder(List list, BinaryTreePosition v) {
        if (null == v) return;
        postorder(list, v.getLChild());
        postorder(list, v.getRChild());
        list.insertLast(v);
    }

    // 层次遍历以 v 为根节点的子树
    protected static void levelorder(List list, BinaryTreePosition v) {
        QueueOnList queue = new QueueOnList();
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            BinaryTreePosition u = (BinaryTreePosition) queue.dequeue();
            list.insertLast(u);
            if (u.hasLChild()) queue.enqueue(u.getLChild());
            if (u.hasRChild()) queue.enqueue(u.getRChild());
        }
    }
}
