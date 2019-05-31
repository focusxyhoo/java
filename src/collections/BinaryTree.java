package collections;

/**
 * 二叉树接口。
 *
 * @author focusxyhoo
 * @date 2019-05-24 16:00
 */
public interface BinaryTree {
    // 返回树根
    BinaryTreePosition getRoot();

    boolean isEmpty();

    int getSize();

    int getHeight();

    // 前序遍历
    Iterator elementsPreorder();

    // 中序遍历
    Iterator elementsInorder();

    // 后序遍历
    Iterator elementsPostorder();

    // 层次遍历
    Iterator elementsLevelorder();
}
