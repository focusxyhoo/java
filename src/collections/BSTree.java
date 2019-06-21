package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-06-05
 * time        : 17:16
 * description : 基于链表式 BST 实现的词典结构。基于 BinaryTree 进行扩充。
 */
public class BSTree extends BinaryTreeLinkedList implements Dictionary {

    /******************************* 实例变量 **************************************/
    protected Comparator C;
    protected BinaryTreePosition lastV; // 最后操作的节点，以便 AVL 树、伸展树重平衡

    /******************************* 构造方法 **************************************/
    public BSTree() {
        this(null, new DefaultComparator());
    }

    public BSTree(BinaryTreePosition r) {
        this(r, new DefaultComparator());
    }

    public BSTree(BinaryTreePosition r, Comparator c) {
        root = r;
        C = c;
    }

    /*******************************  Directory 接口方法 **************************************/
    @Override
    public Entry find(Object key) {
        if (isEmpty()) return null;
        BSTreeNode u = binSearch((BSTreeNode) root, key, C);
        // 需要对 u 再进行一次检查。
        return (0 == C.compare(key, u.getKey())) ? (Entry) u.getElement() : null;
    }

    @Override
    public Iterator findAll(Object key) {
        List list = new ListOnDLNode();
        findAllNodes((BSTreeNode) root, key, list, C);
        return list.elements();
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry entry = new DefaultEntry(key, value);
        if (isEmpty()) {
            // 插入根节点
            lastV = root = new BSTreeNode(entry, null, true, null, null);
        } else {
            // 插入一般节点的情况
            BSTreeNode p = (BSTreeNode) root;
            boolean asLeftChild; // 标记新节点是否作为左孩子插入

            while (true) {
                p = binSearch(p, key, C);
                if (C.compare(key, p.getKey()) < 0) {
                    asLeftChild = true;
                    break;
                } else if (C.compare(key, p.getKey()) > 0) {
                    asLeftChild = false;
                    break;
                } else if (!p.hasLChild()) {
                    asLeftChild = true;
                    break;
                } else if (!p.hasRChild()) {
                    asLeftChild = false;
                    break;
                } else
                    p = (BSTreeNode) p.getLChild();
            }
            lastV = new BSTreeNode(entry, p, asLeftChild, null, null);
        }
        return entry;
    }

    @Override
    public Entry remove(Object key) {
        if (isEmpty()) return null;

        BinaryTreePosition v = binSearch((BSTreeNode) root, key, C);
        if (0 != C.compare(key, ((BSTreeNode) v).getKey())) return null;

        // 至此，查找必成功，接下来需要删除节点 v
        if (v.hasLChild()) {
            BinaryTreePosition w = v.getPrev();
            w.setElement(v.setElement(w.getElement()));
            v = w;
        }

        // 至此， v 至多只有一个孩子。删除 v，代之以其孩子。
        lastV = v.getParent();
        BinaryTreePosition u = v.hasLChild() ? v.getLChild() : v.getRChild();
        if (null == lastV) {
            if (null != u)
                u.secede();
            root = u;
        } else {
            if (v.isLChild()) {
                v.secede();
                lastV.attachL(u);
            } else {
                v.secede();
                lastV.attchR(u);
            }
        }
        return (Entry) v.getElement();
    }

    @Override
    public Iterator entries() {
        List list = new ListOnDLNode();
        concatenate(list, (BSTreeNode) root);
        return list.elements();
    }

    /******************************* 辅助方法 **************************************/
    /**
     * 在以 v 为根的子树中查找关键码为 key 的节点。
     * 假设子树不为空。若找到，返回该节点；否则返回被访问的上一个节点。
     * 因此，调用者需要再检查一遍返回的结果。
     *
     * @param v
     * @param key
     * @param c
     * @return
     */
    protected static BSTreeNode binSearch(BSTreeNode v, Object key, Comparator c) {
        BSTreeNode u = v;
        while (true) {
            int comp = c.compare(key, u.getValue());
            if (comp < 0)
                if (u.hasLChild()) u = (BSTreeNode) u.getLChild();
                else return u;
            else if (comp > 0)
                if (u.hasRChild()) u = (BSTreeNode) u.getRChild();
                else return u;
            else return u;
        }
    }

    /**
     * 在以 v 为根的子树中递归地找出关键码为 key 的所有节点。
     * 这些节点被加入 list 当中以生成迭代器。
     *
     * @param root
     * @param key
     * @param list
     * @param c
     */
    protected static void findAllNodes(BSTreeNode root, Object key, List list, Comparator c) {
        if (null == root) return; // 递归基
        int comp = c.compare(key, root.getValue());
        if (0 >= comp) findAllNodes((BSTreeNode) root.getLChild(), key, list, c);
        if (0 == comp) list.insertLast(root);
        if (0 <= comp) findAllNodes((BSTreeNode) root.getRChild(), key, list, c);
    }

    /**
     * 将 root 的所有后代节点组织为一个列表，以生成迭代器。
     *
     * @param list
     * @param root
     */
    private static void concatenate(List list, BSTreeNode root) {
        if (null == root) return;
        concatenate(list, (BSTreeNode) root.getLChild());
        list.insertLast(root);
        concatenate(list, (BSTreeNode) root.getRChild());
    }
}
