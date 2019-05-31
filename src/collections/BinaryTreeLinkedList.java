package collections;

/**
 * 基于链表实现的二叉树结构。
 *
 * @author focusxyhoo
 * @date 2019-05-25 21:39
 */
public class BinaryTreeLinkedList implements BinaryTree {

    protected BinaryTreePosition root;

    /******************************* 构造方法 **************************************/
    public BinaryTreeLinkedList() {
        this(null);
    }

    public BinaryTreeLinkedList(BinaryTreePosition root) {
        this.root = root;
    }

    /******************************* BinaryTree 接口方法 **************************************/
    @Override
    public BinaryTreePosition getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return null == root;
    }

    @Override
    public int getSize() {
        return isEmpty() ? 0 : root.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty() ? -1 : root.getHeight();
    }

    @Override
    public Iterator elementsPreorder() {
        return root.elementsPreorder();
    }

    @Override
    public Iterator elementsInorder() {
        return root.elementsInorder();
    }

    @Override
    public Iterator elementsPostorder() {
        return root.elmentsPostorder();
    }

    @Override
    public Iterator elementsLevelorder() {
        return root.elementsLevelorder();
    }
}
