package math;

/**
 * 1 点到线段距离
 * 2 判断一个点是否在复杂多边形内部
 */
public class PointToSegment {

  // 向量法 http://blog.sina.com.cn/s/blog_5d5c80840101bnhw.html
  public static double PointToSegDist1(double x, double y, double x1, double y1, double x2, double y2)
  {
    double cross = (x2 - x1) * (x - x1) + (y2 - y1) * (y - y1);
    if (cross <= 0) return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    double d2 = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    if (cross >= d2) return Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
    double r = cross / d2;
    double px = x1 + (x2 - x1) * r;
    double py = y1 + (y2 - y1) * r;
    return Math.sqrt((x - px) * (x - px) + (py - y1) * (py - y1));
  }

  static class Point {
    int x, y;
    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  // 法2： 海伦公式
  static int pointToSegDist2(Point p1, Point p2, Point p0) {
    int x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y, x0 = p0.x, y0 = p0.y;
    double space = 0;
    double a, b, c;
    a = lineSpace(x1, y1, x2, y2);
    b = lineSpace(x1, y1, x0, y0);
    c = lineSpace(x2, y2, x0, y0);
    if (c <= 0.000001 || b <= 0.000001) { // p0与p1或p2重合，距离为0
      return  0;
    }
    else if (a <= 0.000001) { // p1与p2重合，返回p0到p1距离
      space = b;
    }
    else if (c * c >= a * a + b * b) { // p0p2为最长边的钝角三角形，返回p0到p1距离
      space = b;
    }
    else if (b * b >= a * a + c * c) { // // p0p1为最长边的钝角三角形，返回p0到p2距离
      space = c;
    }
    else { //锐角三角形，求垂线长度
      double p = (a + b + c) / 2;
      // 海伦公式，求出三角形面积
      double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
      space = 2 * s / a;
    }
    return (int)Math.round(space);
  }

  /**
   * @return 两点间距离的平方
   */
  public static double lineSpace(int x1, int y1, int x2, int y2) {
    // 向量模长
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }

  /**
   * 判断一个点是否在复杂多边形内部
   */
  public static boolean isInPolygon(Point point, Point[] points) {
    int num = 0;
    int n = points.length;
    for (int i = 0; i < n; i++) {
      Point p1 = points[i];
      Point p2 = points[(i + 1) % n];
      // 判断方法： 引一条射线（为方便取平行与x轴正方向的射线），求与多边形边（线段）的交点个数，若交点个数为奇数，则必然在多边形内部
      if (p1.y == p2.y)
        continue;
      if (point.y < Math.min(p1.y, p2.y))
        continue;
      if (point.y >= Math.max(p1.y, p2.y))
        continue;
      // 求交点横坐标
      double x = (double) (point.y - p1.y) * (double) (p2.x - p1.x) / (double) (p2.y - p1.y) + p1.x;
      if (x > point.x)
        num++;
    }
    return (num % 2 == 1);
  }
}
