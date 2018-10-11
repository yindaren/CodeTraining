package math;

/**
 * 最大公约数，最小公倍数：辗转相除法
 */
public class CommonDivisorMultiple {
  public static long commonDivisor(long n, long m) {
    while (n % m != 0) {
      long temp = n % m;
      n = m;
      m = temp;
    }
    return m;
  }

  public static long commonMultiple(long n, long m) {
    return n * m / commonDivisor(n, m);
  }
}
