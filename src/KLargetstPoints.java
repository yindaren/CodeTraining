/**
 * 给定一些 points 和一个 origin，从 points 中找到 k 个离 origin 最近的点。
 * 按照距离由小到大返回。如果两个点有相同距离，则按照x值来排序；若x值也相同，就再按照y值排序。
 * 距离为欧式距离
 *
 * 方法一：这里直接用所有的数据（N）建最小堆，取前k个最小点，复杂度为O（klogN）。也可只维护一个规
 * 模为k的最大堆（始终保持最小的k个点，设其根为n），不断向其中添加新的点m，若m>=n直接抛弃m，否则
 * 用m代替n，重新使最大堆满足条件，得到新的n。不断重复该过程，复杂度为O（Nlogk）
 *
 * 方法二：先构建一棵有k个叶子节点的败者树，胜出者w是k个中最大的那一个。从N中读入一个新的数m后，
 * 和w比较，如果大于等于w，直接丢弃，否则用m替换w所在的叶子节点的值，然后维护该败者树。依次执行，
 * 直到遍历完N，败者树中保留的k个数就是N中最小的k个数，时间复杂度也是O（Nlogk）。类似上面用最大堆
 *
 * 类快速排序法找最大的k个数：类似快速排序，找一个“轴”节点，将数列交换变成两部分，一部分全都小于等于“轴”，
 * 另一部分全都大于等于“轴”，如果我们选择的轴使得交换完的“较大”那一部分的数的个数j正好是n，不也就完成了
 * 在N个数中寻找n个最大的数的任务吗？当然，轴也许不能选得这么恰好。可以这么分析，如果j>n，则最大的n个数肯定
 * 在这j个数中，则问题变成在这j个数中找出n个最大的数；否则如果j<n，则这j个数肯定是n个最大的数的一部分，而剩
 * 下的j-n个数在小于等于轴的那一部分中，同样可递归处理。算法的平均复杂度是O（N），在每次都能平均划分的情况下；
 * 最坏情况每次划分成N-1：0，复杂度为O（kN）或O（（N-k）N）
 */
public class KLargetstPoints {

  static class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }

    @Override
    public String toString() {
      return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
    }
  }

  public static int compare(double[] dis, int[] index, Point[] points, int a, int b) {
    if (dis[a] > dis[b]) {
      return 1;
    } else if (dis[a] == dis[b]) {
      if (points[index[a]].x > points[index[b]].x) {
        return 1;
      } else if (points[index[a]].x == points[index[b]].x) {
        if (points[index[a]].y > points[index[b]].y) {
          return 1;
        }
      }
    }
    return -1;
  }

  public static void minHeap(double[] dis, int[] index, Point[] points, int i, int len) { //键堆步骤
    int m =  2 * i + 1;
    if (m >= len) {
      return;
    }
    int p = i;
    int n = m + 1;
    if (n < len && compare(dis, index, points, m, n) > 0 ) {
        m = n;
      }

    if (compare(dis, index, points, p, m) > 0) {
      double tmp1 = dis[p];
      int tmp2 = index[p];
      dis[p] = dis[m];
      dis[m] = tmp1;
      index[p] = index[m];
      index[m] = tmp2;
      minHeap(dis, index, points, m, len);
    }
  }

  public static void out(int[] index) { //输出index数组
    int len = index.length;
    String str = "";
    for (int i = 0; i< len; i++) {
      str += String.valueOf(index[i]);
    }
    System.out.println(str);
  }

  /**
   * @param points a list of points
   * @param origin a point
   * @param k an integer
   * @return the k closest points
   */
  public static Point[] kClosest(Point[] points, Point origin, int k) {
    // Write your code here
    if (k<=0) {
      return null;
    }
    Point[] res = new Point[k];
    int len = points.length;
    double[] dis = new double[len];//heap数组
    int[] index = new int[len];//将heap数组与原来点位置关联（其实可以写一个类直接包含point与距离）
    for (int i = 0; i< len; i++) {
      dis[i] = (points[i].x-origin.x) * (points[i].x-origin.x) + (points[i].y-origin.y) * (points[i].y-origin.y);
      index[i] = i;
    }

    for (int i = (len-1) / 2; i >=0; i--) {//建堆
      minHeap(dis, index, points, i, len);
    }

    int length = len;
    for (int i = 0; i < k && i < length; i++) {//取k个最小值
      res[i] = points[index[0]];
      index[0] = index[len - 1];
      dis[0] = dis[-- len];
      minHeap(dis, index, points, 0, len);
    }
    return res;
  }

  public static void main(String[] args) {
    Point[] points = {new Point(4,6), new Point(4, 7),
          new Point(4, 4), new Point(2, 5), new Point(1, 1)};
//    Point[] points = {new Point(0,0), new Point(0, 9)};
    Point original = new Point(3, 1);
    int k = 10;
    for (Point p : kClosest(points, original, k ))
      System.out.println(p);
  }

}
