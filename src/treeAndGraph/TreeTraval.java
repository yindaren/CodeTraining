package treeAndGraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的前序遍历，中序遍历，后序遍历，层次遍历，
 * 树与图的深度优先，广度优先
 *
 * 前序遍历本质上即深度优先，层次遍历本质上即广度优先
 */
public class TreeTraval {

    // 前序遍历递归方法，根左右
    public static void preHelp(Node n) {
        if(n==null) return;
        n.visit();
        if(n.left != null)
            preHelp(n.left);
        if(n.right != null)
            preHelp(n.right);
    }

    public static void pre(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        Node pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                pNode.visit();
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                Node node = stack.pop();
                pNode = node.right;
            }
        }
    }

    // 中序遍历递归方法，左根右
    public static void inHelp(Node n) {
        if(n==null) return;
        if(n.left != null)
            inHelp(n.left);
        n.visit();
        if(n.right != null)
            inHelp(n.right);
    }

    public static void in(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        Node pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                Node node = stack.pop();
                node.visit();  // 与前序遍历只有这里有区别
                pNode = node.right;
            }
        }
    }

    // 后序遍历递归方法，左右根
    public static void suffixHelp(Node n) {
        if(n==null) return;
        if(n.left != null)
            suffixHelp(n.left);
        if(n.right != null)
            suffixHelp(n.right);
        n.visit();
    }

    public static void suffix(Node root) {
        //todo:后序遍历非递归
    }

    public static void level(Node root) {
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();// 正常层次遍历用queue
        q.offer(root);
        while(!q.isEmpty()) {
            Node tmp = q.poll();
            tmp.visit();
            if(tmp.left != null) q.offer(tmp.left);
            if(tmp.right != null) q.offer(tmp.right);
        }
    }

    // dfs递归法， 与前序遍历完全相同
    public static void dfsHelp(Node n) {
        if(n==null) return;
        n.visit(); // 深度优先直接访问节点n

        //树的实现
        if(n.left != null)
            dfsHelp(n.left);
        if(n.right != null)
            dfsHelp(n.right);

        //图的实现
        for(Node child: n.children) {
            dfsHelp(child);
        }
    }

    public static void dfs(Node root) {
        // 非递归法参照前序遍历非递归法
    }

    public static void bfs(Node root) {
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();// 正常层次遍历用queue
        q.offer(root);
        while(!q.isEmpty()) {
            Node tmp = q.poll();
            tmp.visit();
            for(Node child: tmp.children) {
                q.offer(child);
            }
        }
    }
}
