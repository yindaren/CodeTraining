/**
 * 搜索相关
 */
public class Search {
    /**
     * 二分搜索，有重复的随机返回
     * @start inclusive
     * @end exclusive
     */
    public static int binarySearch(int[] num, int start, int end, int target) {
        if (end <= start) return - start - 1;//未找到返回：若应该插入的位置为i，则返回 - i - 1
        int mid = (end + start) / 2;
        if (num[mid] == target) {
            return mid;
        } else if (num[mid] > target){
            return binarySearch(num, start, mid, target);
        } else {
            return binarySearch(num, mid + 1, end, target);
        }
    }

    /**
     * 二分搜索，有重复返回第一个,非递归
     * @start inclusive
     * @end exclusive
     */
    public static int binarySearch_first(int[] num, int start, int end, int target) {
        int low = start, up = end - 1;
        if (up < low) {
            return - low - 1;
        }
        int p = end;
        //if (end <= start) return - start - 1;
        while (low < up) {
            int mid = (low + up)/2;
            if (num[mid] < target) {
                low = mid + 1;
            } else {
                up = mid;
            }
        }
        if (num[up] == target) {
            return up;
        } else if (num[up] >= target) {//未找到返回同上
            return - low - 1;
        } else {
            return - low - 2;
        }
    }

    public static void main (String[] args) {
        int[] tmp = new int[]{1, 2, 4, 4, 4, 6, 6, 6, 6, 7, 11};
        System.out.println(Search.binarySearch(tmp, 3, 10, 8));
        System.out.println(Search.binarySearch_first(tmp, 3, 10, 8));
    }
}
