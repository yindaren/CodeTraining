package javaBasic;

import util.Utils;

public class ByteTest {
  public static void main() {
    Byte a = 126;
    int b = 1;// byte, char等操作会升级为int，故b不能为byte类型
    b = b + a ;
    Utils.println(1);
  }
}
