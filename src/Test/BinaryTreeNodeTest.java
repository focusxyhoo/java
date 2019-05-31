package Test;

import collections.BinaryTreeNode;
import collections.BinaryTreePosition;
import collections.Iterator;

/**
 * 测试 BinaryTreeNode 的实现。
 * 构造了一个如下所示的二叉树：
 *              A
 *             / \
 *            B  C
 *           / \  \
 *          D  E  F
 *
 * @author focusxyhoo
 * @date 2019-05-24 17:20
 */
public class BinaryTreeNodeTest {
    public static void main(String[] args) {
        // 构建二叉树的结构
        BinaryTreePosition nodeA = new BinaryTreeNode('A', null, true, null, null);
        BinaryTreePosition nodeB = new BinaryTreeNode('B', nodeA, true, null, null);
        BinaryTreePosition nodeC = new BinaryTreeNode('C', nodeA, false, null, null);
        BinaryTreePosition nodeD = new BinaryTreeNode('D', nodeB, true, null, null);
        BinaryTreePosition nodeE = new BinaryTreeNode('E', nodeB, false, null, null);
        BinaryTreePosition nodeF = new BinaryTreeNode('F', nodeC, false, null, null);
        nodeA.attachL(nodeB);
        nodeA.attchR(nodeC);
        nodeB.attachL(nodeD);
        nodeB.attchR(nodeE);
        nodeC.attchR(nodeF);

        System.out.println(nodeA.getHeight());
        System.out.println(nodeA.getSize());
        System.out.println(nodeD.getHeight());

        Iterator pre = nodeA.elementsPreorder();
        Iterator in = nodeA.elementsInorder();
        Iterator post = nodeA.elmentsPostorder();
        Iterator level = nodeA.elementsLevelorder();

        System.out.print("nodeA 的前序遍历为：");
        while (pre.hasNext()) System.out.print(pre.getNext() + " ");
        System.out.print("\nnodeA 的中序遍历为：");
        while (in.hasNext()) System.out.print(in.getNext() + " ");
        System.out.print("\nnodeA 的后序遍历为：");
        while (post.hasNext()) System.out.print(post.getNext() + " ");
        System.out.print("\nnodeA 的层序遍历为：");
        while (level.hasNext()) System.out.print(level.getNext() + " ");

    }
}
