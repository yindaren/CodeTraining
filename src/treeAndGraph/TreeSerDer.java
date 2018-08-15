package treeAndGraph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对二叉树进行序列化，反序列化
 */
public class TreeSerDer {

    // 前序遍历序列化
    public static String ser1(Node root) {
        StringBuilder sb = new StringBuilder();
        if(root == null){
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.value + ",");
        sb.append(ser1(root.left));
        sb.append(ser1(root.right));
        return sb.toString();
    }

    static  int index = -1;
    // 前序遍历反序列化
    public static Node des1(String str) {
        index++;
        String[] strr = str.split(",");
        Node node = null;
        if(!strr[index].equals("#")){
        node = new Node(Integer.valueOf(strr[index]));
        node.left = des1(str);
        node.right = des1(str);
        }
        return node;
    }

    // 层次遍历序列化
    public static String ser2(Node root) {
        if(root == null) return "s";
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(String.valueOf(root.value) + ",");
        while(!queue.isEmpty()) {
            Node tmp = queue.poll();
            if(tmp.left == null) {
                sb.append("s,");
            }
            else {
                sb.append(String.valueOf(tmp.left.value) + ",");
                queue.offer(tmp.left);
            }

            if(tmp.right == null) {
                sb.append("s,");
            }
            else {
                sb.append(String.valueOf(tmp.right.value) + ',');
                queue.offer(tmp.right);
            }
        }
        return sb.toString();
    }

    // 层次遍历反序列化
    public static Node der2(String str) {
        if(str == null || str.length() == 0 || str.equals("s")) return null;
        String[] strs = str.split(",");
        Node result = new Node(Integer.valueOf(strs[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(result);
        int i = 0, n = strs.length;
        while(!queue.isEmpty()) {
            int count = queue.size();
            for(int j=0;j<count;j++) { // 不能用j<queue.size(), size每次会重算
                Node tmp = queue.poll();
                if(!strs[++i] .equals("s")) {
                    tmp.left = new Node(Integer.valueOf(strs[i]+""));
                    queue.offer(tmp.left);
                }
                if(!strs[++i] .equals("s")) {
                    tmp.right = new Node(Integer.valueOf(strs[i]+""));
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
        // 从根节点分成两段继续递归
        curRoot.left = help3(pre, in, rootIndex+1, start, i-1); // 找到下一个根节点, 第一个直接加1
        curRoot.right = help3(pre, in, rootIndex+i-start+1, i+1, end);// 第二个
        return curRoot;
    }

    // 前序加中序反序列化。这种方法要求树中节点值不能重复
    public static Node der3(int[] pre, int[] in) {
        int n = pre.length;
        return help3(pre, in, 0, 0, n-1);
    }


}
