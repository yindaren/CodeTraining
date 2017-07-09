/**
 * Created by yin on 2017/7/8.
 */
public class KLargetstPoints {

  class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
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
    double[] dis = new double[len];
    int[] index = new int[len];
    int[] index2 = new int[len];
    for (int i = 0; i< len; i++) {
      dis[i] = (points[i].x-origin.x)^2 + (points[i].y-origin.y)^2;
      index[i] = i;

    }

    for (int i = 0; i < (len-1) / 2; i++) {
      int m =  2 * i + 1;
      int p = i;
      while (m < len) {
        int n = m + 1;
        double j = dis[m];
        if (n < len) {
          if (j > dis[n]) {
            j = dis[n];
            m = n;
          }
        }
        if (dis[p] > j) {
          double tmp1 = dis[i];
          int tmp2 = i;
          dis[p] = dis[m];
          dis[m] = tmp1;
          index[p] = m;
          index[m] = p;
          p = m;
          m = 2 * p +1;
        } else {
          break;
        }
      }
    }
    for (int i = 0; i < k && i < len; i++) {
      res[i] = points[index[0]];
      index[0] = index[len - 1];
      dis[0] = dis[-- len];
      int m = 1;
      int p = 0;
      while (m < len) {
        int n = m + 1;
        double j = dis[m];
        if (n < len) {
          if (j > dis[n]) {
            j = dis[n];
            m = n;
          }
        }
        if (dis[p] > j) {
          double tmp1 = dis[i];
          int tmp2 = i;
          dis[p] = dis[m];
          dis[m] = tmp1;
          index[p] = m;
          index[m] = p;
          p = m;
          m = 2 * p +1;
        } else {
          break;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Point points = {new Point(1,4)};
    kClosest();
  }

}
