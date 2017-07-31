import java.util.HashMap;

/**
 * 给出二维平面上的n个点，求最多有多少点在同一条直线上。
 */
public class MaxPointsOnLine {

    static class Point {
        int x;
        int y;
        Point() {
            x = 0; y = 0;
        }
        Point(int a, int b) {
            x = a; y = b;
        }
    }

    /**
     * O(n^2)的时间复杂度，利用2点求斜率（对于点points[i]），map保存各斜率值的计数。
     * 需要注意的是2点元素相等和斜率不存在的情况
     */
    public int maxPoints(Point[] points) {

        if(points==null||points.length==0){
            return 0;
        }
        int max=1;
        //用到了map集合
        HashMap<Double,Integer> map=new HashMap<>();
        for(int i=0;i<points.length;i++){
            map.clear();

            map.put((double)Integer.MIN_VALUE, 1);

            int dou=0;

            for(int j=i+1;j<points.length;j++){
                if(points[j].x==points[i].x&&points[j].y==points[i].y){
                    dou++;
                }else{
                    double key=(points[j].x-points[i].x==0 ? (double)Integer.MAX_VALUE :
                            0.0+(double)(points[j].y-points[i].y)/(double)(points[j].x-points[i].x));
                    if(map.containsKey(key)){
                        map.put(key, map.get(key)+1);
                    }else{
                        map.put(key,2);
                    }
                }
            }
            for(int temp:map.values()){
                if((temp+dou)>max){
                    max=temp+dou;
                }
            }

        }

        return max;
    }
}
