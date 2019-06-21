package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-06-19
 * time        : 15:08
 * description : AVL 树：基于 BSTree 的扩充
 */
public class AVLTree extends BSTree implements Dictionary {


    /******************************* 构造方法 **************************************/
    public AVLTree() {
        super();
    }

    public AVLTree(BinaryTreePosition r) {
        super(r);
    }

    public AVLTree(BinaryTreePosition r, Comparator c) {
        super(r, c);
    }

    /******************************* 词典方法（覆盖父类方法） **************************************/
    @Override
    public Entry insert(Object key, Object value) {
        Entry entry = super.insert(key, value);

        return null;
    }

    @Override
    public Entry remove(Object key) {
        return null;
    }


    /******************************* 辅助方法 **************************************/
    protected static BinaryTreePosition rebalance(BinaryTreePosition z, BinaryTreePosition r) {
        if (null == z) {
            return r;
        }
        while (true) {
            if (!isBalanced(z)) {

            }
        }
    }

    /**
     * 判断当前节点是否平衡。
     *
     * @param v
     * @return
     */
    protected static boolean isBalanced(BinaryTreePosition v) {
        if (null == v) return true;

        int lH = (v.hasLChild()) ? (v.getLChild().getHeight()) : -1;
        int rH = (v.hasRChild()) ? (v.getRChild().getHeight()) : -1;
        int deltaH = lH - rH;

        return (-1 <= deltaH) && (deltaH <= 1);
    }


    protected static BinaryTreePosition rotate(BinaryTreePosition z) {
        BinaryTreePosition y = tallerChild(z);
        BinaryTreePosition x = tallerChild(y);
        boolean cType = z.isLChild();
        BinaryTreePosition p = z.getParent();
        BinaryTreePosition a, b, c;
        BinaryTreePosition t0, t1, t2, t3;

        // 以下分四种情况
        if (y.isLChild()) {
            c = z;
            t3 = z.getRChild();
            if (x.isLChild()) {
                b = y;
                t2 = y.getRChild();
                a = x;
                t1 = x.getRChild();
                t0 = (BSTreeNode) x.getLChild();
            } else {

            }
        }

    }

    /**
     * 返回当前节点的孩子中最高的。
     *
     * @param v
     * @return
     */
    protected static BinaryTreePosition tallerChild(BinaryTreePosition v) {
        int lH = v.hasLChild() ? v.getLChild().getHeight() : -1;
        int rH = v.hasRChild() ? v.getRChild().getHeight() : -1;

        if (lH > rH) return v.getLChild();
        if (lH < rH) return v.getRChild();

        if (v.isLChild()) return v.getLChild();
        else return v.getRChild();
    }

    protected static BinaryTreePosition shorterChild(BinaryTreePosition v) {
        int lH = v.hasLChild() ? v.getLChild().getHeight() : -1;
        int rH = v.hasRChild() ? v.getRChild().getHeight() : -1;

        if (lH > rH) return v.getRChild();
        if (lH < rH) return v.getLChild();

        if (v.isLChild()) return v.getRChild();
        else return v.getLChild();
    }
}
