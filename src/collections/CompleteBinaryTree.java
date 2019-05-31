package collections;

/**
 * 完全二叉树接口
 * 为了保证完全二叉树的结构不被破坏，对其的修改操作只能局限于下面这两个操作。
 *
 * @author focusxyhoo
 * @date 2019-05-25 23:27
 */
public interface CompleteBinaryTree extends BinaryTree {

    // 生成并返回一个节点，其元素为 e，成为新的末节点
    BinaryTreePosition addLast(Object o);

    // 删除末节点，并返回其中存放的内容。
    Object deleteLast();

    // 返回按照层次遍历编号为 i 的节点的位置
    // 注意 i 的范围 0<= i < size()
    BinaryTreePosition poseOfNode(int i);
}
