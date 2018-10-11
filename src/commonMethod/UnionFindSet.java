package commonMethod;

/**
 * 并查集，从树的角度理解
 */
public class UnionFindSet {
  static int[] pre;

  public static int find(int x)
  {
    int r=x;
    while(pre[r]!=r) // 若pre[r] = r 说明达到根节点
      r=pre[r];//找到他的前导结点
    int i=x,j;
    while(i!=r)//路径压缩算法
    {
      j=pre[i];//记录x的前导结点
      pre[i]=r;//将i的前导结点设置为r根节点
      i=j;
    }
    return r;
  }

  public static void join(int x,int y)
  {
    int a=find(x);//x的根节点为a
    int b=find(y);//y的根节点为b
    if(a!=b)//如果a,b不是相同的根节点，则说明ab不是连通的
    {
      pre[a]=b;//我们将ab相连 将a的前导结点设置为b
    }
  }
}
