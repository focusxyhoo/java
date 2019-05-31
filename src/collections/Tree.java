package collections;

/**
 * 基于"父亲-长子-弟弟"模型的树接口。
 *
 * @author focusxyhoo
 * @date 2019-05-24 15:15
 */
public interface Tree {

    // 返回当前节点中存放的对象。
    Object getElement();

    // 将对象 object 存入当前节点，并返回此前的内容。
    Object setElement(Object object);

    // 返回当前节点的父节点。
    TreeLinkedList getParent();

    // 返回当前节点的长子。
    TreeLinkedList getFirstChild();

    // 返回当前节点的最大弟弟。
    TreeLinkedList getNextSibling();

    // 返回打钱节点后代元素的数目。即以当前节点为根的子树的规模。
    int getSize();

    // 返回当前节点的高度。
    int getHeight();

    // 返回当前节点的深度。
    int getDepth();

}
