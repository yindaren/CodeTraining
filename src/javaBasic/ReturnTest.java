package javaBasic;

import util.Utils;

import java.io.IOException;

public class ReturnTest {
  static int test() {
    int tmp = 0;
    try {
      tmp++;
      // throw new ArrayIndexOutOfBoundsException(); //此处的throw不会抛出，由于finally中的return
      // return tmp;// 若finally中无return，这里会记录返回值为1
    } catch(Exception e) {
      tmp++;
      // throw new ArrayIndexOutOfBoundsException(); //此处的throw不会抛出，由于finally中的return
      // return tmp;
    } finally {
      tmp++;
      return tmp;
    }
  }

  public static void main(String[] args) {
    Utils.println(test());
  }
}
