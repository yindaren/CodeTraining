/**
 * 搜索相关
 */
public class Search {
    /**
     * 二分搜索，数组需要是有序的
     * @start inclusive
     * @end exclusive
     * @return the target key if exits, else
     */
    public int binarySearch(int[] num, int start, int end, int target) {
        if (end <= start) return - start - 1;//返回应该插入的位置的负数 - 1
        int mid = (end + start)/2;
        if (num[mid] == target) {
            return mid;
        } else if (num[mid] > target){
            return binarySearch(num, start, mid, target);
        } else {
            return binarySearch(num, mid + 1, end, target);
        }
    }
}
