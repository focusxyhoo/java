package collections;

/**
 * 基于向量实现的完全二叉树
 *
 * @author focusxyhoo
 * @date 2019-05-26 19:05
 */
public class CompleteBinaryTreeOnVector extends BinaryTreeLinkedList implements CompleteBinaryTree {
    private Vector T;

    /******************************* 构造方法 **************************************/
    // 默认构造方法
    public CompleteBinaryTreeOnVector() {
        T = new VectorOnExtendableArray();
        root = null;
    }

    // 按照给定节点序列来建立完全二叉树
    public CompleteBinaryTreeOnVector(Sequence sequence) {
        this();
        if (null != sequence) {
            while (!sequence.isEmpty()) {
                addLast(sequence.removeFirst());
            }
        }
    }


    /******************************* BinaryTree接口方法 **************************************/
    @Override
    public BinaryTreePosition getRoot() {
        return T.isEmpty() ? null : poseOfNode(0);
    }

    @Override
    public boolean isEmpty() {
        return T.isEmpty();
    }

    @Override
    public int getSize() {
        return T.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty() ? -1 : getRoot().getHeight();
    }


    /******************************* CompleteBinaryTree接口方法 **************************************/
    @Override
    public BinaryTreePosition addLast(Object o) {
        BinaryTreePosition node = new CompleteBinaryNodeOnRank(T, o);
        root = (BinaryTreePosition) T.getAtRank(0);
        return node;
    }

    @Override
    public Object deleteLast() {
        if (isEmpty()) return null;
        if (1 == getSize()) root = null;
        return T.removeAtRank(T.getSize() - 1);
    }

    @Override
    public BinaryTreePosition poseOfNode(int i) {
        return (BinaryTreePosition) T.getAtRank(i);
    }
}
