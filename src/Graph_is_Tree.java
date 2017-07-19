/**
 * 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 判断这张无向图是否是一棵树
 *
 * 样例：
 * 给出n = 5 并且 edges = [[0, 1], [0, 2], [0, 3], [1, 4]], 返回 true.
 * 给出n = 5 并且 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], 返回 false.
 *
 * 思路：图为树 = 边数小于点数 + 数组存根的方法判断联通
 */
public class Graph_is_Tree {

    //给每一个节点找到最顶层的根，同时可以减少查询某一个点的根时的循环次数
    private int setTopRoot(int[] graph, int i) {
        if (graph[i] != i) {
            return graph[i] = setTopRoot(graph, graph[i]);
        } else {
            return i;
        }
    }

    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n <= 1) {
            return true;
        }
        int[] root = new int[n];//a[1] = 2 表示节点1的父节点为节点2
        for (int i = 0; i < n; i ++) {
            root[i] = i;
        }
        if (edges.length >= n) { //边数大于等于n不可能为树
            return false;
        }
        for (int i = 0; i< edges.length; i ++) {
            int a = edges[i][0];
            int b = edges[i][1];
            setTopRoot(root, a);
            setTopRoot(root, b);
            if (root[b] == b){
                root[b] = root[a];
            } else if (root[a] == a){
                root[a] = root[b];
            } else {
                root[root[b]] = root[a];//注意这里
            }
        }
        int flag = -1;
        for (int i = 0; i < n; i++) {//这里也可以统计出有多少联通子图
            int k = root[i];
            int child = i;
            while (k != child) {
                child = k;
                k = root[k];
            }
            if (flag < 0) {
                flag = k;
                continue;
            }
            if (flag != k) {
                return false;
            }
        }
        return true;
    }
}
