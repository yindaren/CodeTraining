package math;

/**
 * 平方根与立方根
 */
public class Root {

  /**
   * 牛顿迭代法, 切线逼近
   */
  static double sqrt1(double x, double precision) {
    double t = x;
    while(Math.abs(t-x/t) > precision/t) { //防止溢出
      t = (t + x/t)/2;
    }
    return t;
  }

  /**
   * 用二分法逼近
   */
  static double sqrt2(double x, double precision) {
    double t = x;
    while(Math.abs(t-x/t) > precision/t) { //防止溢出
      t = (x/t + t)/2;
    }
    return t;
  }

  public static double cbrt(double c, double precision)
  {
    boolean b = c>0;
    c = Math.abs(c);
    double t = c;
    while(Math.abs(t*t-c/t) > precision)
      t = (c/(t*t)+t)/2.0;
    t = (b) ? t : -t;
    return t;
  }

  public static void main(String[] args)
  {
    // System.out.println(Math.sqrt(12312312124143423423.0d)); // 直接溢出
    double r = sqrt1(12312312124143423423.0, 0.000001);
    System.out.println("sqrt(4.0) = " + r);
    double rc = cbrt(-27, 0.0001);
     System.out.println("cbrt(9.0) = " + rc);
  }
}
