package treeAndGraph;

import java.util.Stack;

/**
 * 对树进行z字形遍历（奇数层正常层次遍历，偶数层反向遍历）
 */
public class ZShapedTraval {
  static class Node {
    Node left, right;
  }
  
  public static void zTree(Node root) {
    int layer = 1;
    if(root == null) return;
    // Queue<Node> q = new LinkedList<>();// 正常层次遍历用queue
    Stack<Node> s1 = new Stack<>(); // z 字型用两个stack， 一次先放左子树，一次先放右子树
    Stack<Node> s2 = new Stack<>();
    s1.add(root);
    while(!s1.isEmpty() || !s2.isEmpty()) {
      if((layer&1)==1) {
        while(!s1.isEmpty()) {
          Node n = s1.pop();
          // visit(n);
          if(n.left != null) s2.push(n.left);
          if(n.right != null) s2.push(n.right);
        }
      }
      else {
        while(!s2.isEmpty()) {
          Node n = s2.pop();
          // visit(n);
          // should put right first
          if(n.right != null) s1.add(n.right);
          if(n.left != null) s1.add(n.left);
        }
      }
      layer++;
      // visit(n)
    }
  }
}
