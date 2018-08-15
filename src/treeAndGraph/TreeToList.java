package treeAndGraph;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class TreeToList {

    // 核心思路： 中序遍历一颗二叉搜索树是有序的。
    public Node help(Node root, Node last) { // last表示当前链表的最后一个节点
        if(root == null) return last;
        Node curLast = help(root.left, last);
        // 每遍历到一个节点将其链接到链表上
        root.left = curLast;
        if(curLast != null) curLast.right = root;
        return help(root.right, root);
    }

    public Node Convert(Node pRootOfTree) {
        Node tmp = pRootOfTree;
        while(tmp != null && tmp.left != null) { //找到链表头节点
            tmp = tmp.left;
        }
        help(pRootOfTree, null);
        return tmp;
    }
}
