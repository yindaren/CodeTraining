package javaBasic;

import util.Utils;

public class ExceptionTest {
  public static void main(String[] args) throws InterruptedException {
    Runnable t1 = () -> {}; // lambda, 必须为接口

    Thread t = new Thread() {
      @Override
      public void run() { // run 方法不能抛出受检异常
        // 抛出非受检异常将导致线程退出， 但jvm不会挂
        // throw new IndexOutOfBoundsException("exception");
        try {
          // 即使自己抛出error也不会使jvm停止
          throw new OutOfMemoryError("exception");
        } catch (Throwable t) { // 任何异常都可以被捕获
          Utils.println("catch error");
        }
        while(true) {
          Utils.println("thread");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

      }
    };
    t.start();

    // 在main线程抛出异常也不会使jvm退出（main线程退出），未catch的异常都将由java runtime 捕获
    throw new OutOfMemoryError("exception");

//    while(true) {
//      Utils.println("stop");
//      Thread.sleep(1000);
//    }

  }
}
