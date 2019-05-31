package collections;

/**
 * 二叉树节点接口。
 *
 * @author focusxyhoo
 * @date 2019-05-24 16:03
 */
public interface BinaryTreePosition extends Position {

    // 判断是否有父节点
    boolean hasParent();
    // 返回当前父节点
    BinaryTreePosition getParent();
    // 设置当前节点的父节点
    void setParent(BinaryTreePosition parent);

    // 判断是否是叶子节点
    boolean isLeaf();

    // 判断是否是左孩子节点
    boolean isLChild();
    // 判断是否有左孩子节点
    boolean hasLChild();
    // 返回当前节点的左孩子节点
    BinaryTreePosition getLChild();
    // 设置当前节点的左孩子节点（注意：this.lChild 和 lChild.parent 都不一定为空）
    void setLChild(BinaryTreePosition lChild);

    // 同上。
    boolean isRChild();
    boolean hasRChild();
    BinaryTreePosition getRChild();
    void setRChild(BinaryTreePosition rChild);

    // 返回当前节点后代元素的数目
    int getSize();
    // 在孩子发生变化会，更新当前节点及其祖先的规模
    void updateSize();

    // 返回当前节点的高度
    int getHeight();
    // 同上
    void updateHeight();

    // 返回当前节点的深度
    int getDepth();
    void updateDepth();

    // 按照中序遍历的次序，找到当前节点的直接前驱
    BinaryTreePosition getPrev();
    // 按照中序遍历的次序，找到当前节点的直接后继
    BinaryTreePosition getSucc();

    // 注意：断绝当前节点及其父节点的父子关系，返回当前节点呢
    BinaryTreePosition secede();

    // 将节点 c 作为当前节点的左孩子节点
    BinaryTreePosition attachL(BinaryTreePosition c);
    // 将节点 c 作为当前节点的右孩子节点
    BinaryTreePosition attchR(BinaryTreePosition c);

    // 前序遍历
    Iterator elementsPreorder();
    // 中序遍历
    Iterator elementsInorder();
    // 后序遍历
    Iterator elmentsPostorder();
    // 层序遍历
    Iterator elementsLevelorder();
}
