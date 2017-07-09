/**
 * 给定一些 points 和一个 origin，从 points 中找到 k 个离 origin 最近的点。
 * 按照距离由小到大返回。如果两个点有相同距离，则按照x值来排序；若x值也相同，就再按照y值排序。
 * 距离为欧式距离
 *
 * note:使用了最小堆
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
