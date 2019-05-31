package collections;

import exceptions.NoSuchElementException;

/**
 * 基于列表实现的树迭代器。
 * 前序、后序以及层次遍历的时间复杂度均为 O(n)。
 *
 * @author focusxyhoo
 * @date 2019-05-24 15:42
 */
public class IteratorTree implements Iterator {

    private List list;
    private Position nextPosition;

    // 默认构造方法
    public IteratorTree() {
        list = null;
    }

    // 递归方式实现前序遍历。
    public void elementsPreorderIterator(TreeLinkedList treeLinkedList) {
        // 递归基
        if (null == treeLinkedList) return;
        // 首先输出当前节点。
        list.insertLast(treeLinkedList);
        TreeLinkedList subTree = treeLinkedList.getFirstChild();
        while (null != subTree) {
            this.elementsPreorderIterator(subTree);
            subTree = subTree.getNextSibling();
        }
    }

    // 递归方法实现后序遍历。
    public void elementPostorderIterator(TreeLinkedList treeLinkedList) {
        if (null == list) return;
        TreeLinkedList subTree = treeLinkedList.getFirstChild();
        while (null != subTree) {
            this.elementPostorderIterator(subTree);
            subTree = subTree.getNextSibling();
        }
        list.insertLast(treeLinkedList);
    }

    // 层次遍历。
    public void levelTraversalIterator(TreeLinkedList treeLinkedList) {
        if (null == treeLinkedList) return;
        // 空队列。
        QueueOnList queueOnList = new QueueOnList();
        // 根节点入队列。
        queueOnList.enqueue(treeLinkedList);
        // 在队列为空之前重复执行。
        while (!queueOnList.isEmpty()) {
            // 从队列中取出首节点。
            TreeLinkedList t = (TreeLinkedList) queueOnList.dequeue();
            list.insertLast(t);
            TreeLinkedList subTree = t.getFirstChild();
            while (null != subTree) {
                // 依次找出 t 的所有孩子，并添加至队列。
                queueOnList.enqueue(subTree);
                subTree = subTree.getNextSibling();
            }
        }
    }

    @Override
    public boolean hasNext() {
        return null != nextPosition;
    }

    @Override
    public Object getNext() {
        if (!hasNext()) throw new NoSuchElementException("No next position");
        Position currentPosition = nextPosition;
        if (currentPosition == list.last())
            nextPosition = null;
        else nextPosition = list.getNext(currentPosition);
        return currentPosition.getElement();
    }
}
