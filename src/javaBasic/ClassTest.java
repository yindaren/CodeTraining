package javaBasic;

import util.Utils;

public class ClassTest {
  static class A {
    static int a = 1;

    { // 先于构造函数
      Utils.println("A");
    }

    static {
      Utils.println("AS");
    }

    public A() {
      Utils.println("AC");
    }
  }

  static class B extends A{
    {
      Utils.println("B");
    }

    static {
      Utils.println("BS");
    }

    public B() {
      Utils.println("BC");
    }
  }

  public static void main(String[] args) {
    // Utils.println(B.a);// 不会加载类B
    // new A();
    new B();
  }
}
