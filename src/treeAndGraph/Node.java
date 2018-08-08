package treeAndGraph;

import java.util.List;

public class Node {
    final int value;
    Node left, right;
    List<Node> children;

    public Node(int v) {
        value = v;
    }

    public void visit() {}
}
