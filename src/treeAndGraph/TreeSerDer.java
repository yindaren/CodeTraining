package treeAndGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 对二叉树进行序列化，反序列化
 */
public class TreeSerDer {
    public static void help1(Node n, StringBuilder result) {
        if(n==null) result.append( "s");
        else {
            result.append(String.valueOf(n.value));
            help1(n.left, result);
            help1(n.right, result);
        }
    }

    // 前序遍历序列化
    public static String ser1(Node root) {
        StringBuilder result = new StringBuilder();
        help1(root, result);
        return result.toString();
    }

    // 前序遍历反序列化
    public static Node des1(String str) {
        if(str == null || str.length() == 0 || str.equals("s")) return null;
        int n = str.length();
        Stack<Node> stack = new Stack<>();
        Node result = new Node(Integer.valueOf(str.charAt(0)+""));
        Node last = result;
        int i=1;
        stack.push(last);
        while(i<n) {
            char c = str.charAt(i);
            if(c!='s') {
                Node tmp =  new Node(Integer.valueOf(c+""));
                last.left = tmp;
                last = tmp;
                stack.push(last);
            }
            else {
                while(++i<n) {
                    c = str.charAt(i);
                    if(c=='s') {
                        if(!stack.isEmpty())
                            last = stack.pop();
                    }
                    else {
                        Node tmp =  new Node(Integer.valueOf(c+""));
                        last.right = tmp;
                        last = tmp;
                        stack.push(last);
                        break;
                    }
                }
            }
            i++;
        }
        return result;
    }

    // 层次遍历序列化
    public static String ser2(Node root) {
        if(root == null) return "s";
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(String.valueOf(root.value));
        while(!queue.isEmpty()) {
            Node tmp = queue.poll();
            if(tmp.left == null) {
                sb.append("s");
            }
            else {
                sb.append(String.valueOf(tmp.left.value));
                queue.offer(tmp.left);
            }

            if(tmp.right == null) {
                sb.append("s");
            }
            else {
                sb.append(String.valueOf(tmp.right.value));
                queue.offer(tmp.right);
            }
        }
        return sb.toString();
    }

    // 层次遍历反序列化
    public static Node der2(String str) {
        if(str == null || str.length() == 0 || str.equals("s")) return null;
        Node result = new Node(Integer.valueOf(str.charAt(0)+""));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(result);
        int i = 0, n = str.length();
        while(!queue.isEmpty()) {
            int count = queue.size();
            for(int j=0;j<count;j++) { // 不能用j<queue.size(), size每次会重算
                Node tmp = queue.poll();
                if(str.charAt(++i) != 's') {
                    tmp.left = new Node(Integer.valueOf(str.charAt(i)+""));
                    queue.offer(tmp.left);
                }
                if(str.charAt(++i) != 's') {
                    tmp.right = new Node(Integer.valueOf(str.charAt(i)+""));
                    queue.offer(tmp.right);
                }
            }

        }
        return result;
    }

    /**
     * 根据前序遍历和中序遍历的特点，前序遍历找到根节点，中序遍历将数组分成两半，递归执行，每次返回当前递归根节点。
     */
    public static Node help3(int[] pre, int[] in, int rootIndex, int start, int end) {
        if(start>end) return null;
        int root = pre[rootIndex];
        int i = start;
        for(;i<=end;i++) {
            if(in[i] == root) break;
        }
        Node curRoot = new Node(root);
        curRoot.left = help3(pre, in, rootIndex+1, start, i-1); // 找到下一个根节点
        curRoot.right = help3(pre, in, rootIndex+i-start+1, i+1, end);
        return curRoot;
    }

    // 前序加中序反序列化。这种方法要求树中节点值不能重复
    public static Node der3(int[] pre, int[] in) {
        int n = pre.length;
        return help3(pre, in, 0, 0, n-1);
    }
}
