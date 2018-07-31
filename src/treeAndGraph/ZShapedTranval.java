package treeAndGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 对树进行z字形遍历（奇数层正常层次遍历，偶数层反向遍历）
 */
public class ZShapedTranval {
  static class Node {
    Node left, right;
  }
  
  public static void zTree(Node root) {
    int layer = 1;
    if(root == null) return;
    Queue<Node> q = new LinkedList<>();// 正常层次遍历用queue
    Stack<Node> s = new Stack<>(); // z 字型用stack， 一次先放左子树，一次先放又子树
    q.add(root);
    while(!s.isEmpty()) {
      if(layer%2==1) {
        while(!s.isEmpty()) {
          Node n = s.pop();
          //visit(n);
          if(n.left != null) s.push(n.left);
          if(n.right != null) s.push(n.right);
        }
      }
      else {
        while(!s.isEmpty()) {
          Node n = q.remove();
          //visit(n);
          // should put right first
          if(n.right != null) q.add(n.right);
          if(n.left != null) q.add(n.left);
        }
      }
      layer++;

      // visit(n)

    }
  }
}
