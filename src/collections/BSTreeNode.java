package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-06-05
 * time        : 17:08
 * description : 基于链表实现的 BST 节点
 */
public class BSTreeNode extends BinaryTreeNode implements BinaryTreePosition, Entry {

    /******************************* 构造方法 **************************************/
    public BSTreeNode() {
        super();
    }

    public BSTreeNode(Object e,
                      BinaryTreePosition p,
                      boolean asLChild,
                      BinaryTreePosition l,
                      BinaryTreePosition r) {
        super(e, p, asLChild, l, r);
    }

    /******************************* Entry 接口方法 **************************************/
    @Override
    public Object getKey() {
        return ((Entry)getElement()).getKey();
    }

    @Override
    public Object setKey(Object k) {
        return ((Entry)getElement()).setKey(k);
    }

    @Override
    public Object getValue() {
        return ((Entry)getElement()).getValue();
    }

    @Override
    public Object setValue(Object v) {
        return ((Entry)getElement()).setValue(v);
    }
}
