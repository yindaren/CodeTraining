
/**
 * 一些排序，堆排序见{@link KLargetstPoints.minHeap}
 *
 * 基本有序使用插入排序
 * 序列较短时快速排序的效率不高，直接使用快排
 *
 */
public class Sort {

    /**
     * Swaps the postion of a[i] and a[j].
     * @param a the original array
     * @param i the index
     * @param j the index
     */
    public static void swap(int[] a, int i, int j) {
        if (i != j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    /**
     * 插入排序,升序
     * @param a array to be sorted
     * @param start the start index，inclusive
     * @param end the end index,
     */
    public static void insertionSort(int[] a, int start, int end) {
        int len = end - start + 1;
        if (len <= 1) {
            return;
        }
        for (int i = start + 1; i <= end; i ++) {
            int tmp = a[i];
            int j = i;
            while (j > start && tmp < a[j - 1]) { //小于则将a[j - 1]后移移位，不必使用两次while
                a[j] = a[--j];
            }
            a[j] = tmp;
        }
     }

    /**
     * 冒泡排序,升序
     * @param a array to be sorted
     * @param start the start index，inclusive
     * @param end the end index,inclusive
     */
     public static void bubbleSort(int[] a, int start, int end) {
         int len = end - start + 1;
         if (len <= 1) {
             return;
         }
         for (int k = 0; k < len; k++) {//每一次找第k大的数
             int index = start;
             int max = a[start];
             for (int j = start + 1; j <= end - k; j++) {
                 if (a[j] > max) {
                     max = a[j];
                     index = j;
                 }
             }
             swap(a, index, end - k);
         }
     }

    /**
     * 归并排序,升序
     * @param a array to be sorted
     * @param start the start index，inclusive
     * @param end the end index,inclusive
     */
     public static void mergeSort(int[] a, int start, int end) {
         if (start >= end) {
             return;
         }
         int mid = (start + end) / 2;
         mergeSort(a, start, mid);
         mergeSort(a, mid + 1, end) ;
         int[] tmp = new int[end - start + 1];
         int i = start, j = mid + 1, k = 0;
         for (; i <= mid && j <= end; k++) {
             if (a[i] < a[j]) {
                 tmp[k] = a[i];
                 i ++;
             } else {
                 tmp[k] = a[j];
                 j ++;
             }
         }
         while (i <= mid) {
             tmp[k++] = a[i++];
         }
         while (j <= mid) {
             tmp[k++] = a[j++];
         }
         for (i = 0; i < k; i++) {
             a[start + i] = tmp[i];
         }
     }

    /**
     * 快速排序,升序
     * @param a array to be sorted
     * @param start the start index，inclusive
     * @param end the end index,inclusive
     */
    public static void quickSort(int[] a, int start, int end) {
        int len = end - start + 1;
        if (len <= 1) {
            return;
        }
        if (len == 2) {//没必要到只剩一个数，可以配置当只剩下几个数的时候，直接使用插入排序。
            if (a[start] > a[end]) {
                swap(a, start, end);
            }
            return;
        }
        //随机选择一个数（可简化为直接取第一个数）,或使用三数取中，即取start，end，mid中间的数
//        Random random = new Random();
//        int index = start + random.nextInt(len);
//        int m = a[index];
//        a[index] = a[start];//将选中的数与第一个数交换位置

        //这里用三数取中，至少有三个数
        int mid = (start + end) / 2;
        if (a[mid] > a[end]) {
            swap(a, mid, end);
        }
        if (a[start] > a[end]) {
            swap(a, start, end);
        }
        if (a[start] < a[mid]) {
            swap(a, start, mid);
        }

        int key = a[start];
        int s = start;
        int e = end;//a[end]必然大于或等于key
        while (s < e) {
            while (++s < e && a[s] < key);
            if (s == e) {
                break;
            }
            while (--e > s && a[e] >= key);//a[end]必然大于或等于key,可以从end-1开始考虑
            if (e == s) {
                break;
            }
            swap(a, s, e);
        }
        swap(a, start, s - 1);//将key与最后一个小于key的换位
        quickSort(a, start, s - 2);
        quickSort(a, s, end);
    }

    public static void main (StringRelated[] args) {
        int[] k = new int[]{7,6,5,4,7,3,2,1,0,-1};
        //quickSort(k, 0, k.length - 1);
        Sort.quickSort(k, 0, k.length - 1);
        //Sort.bubbleSort(k, 0, k.length - 1);
        //Sort.insertionSort(k, 0, k.length - 1);
        //Sort.mergeSort(k, 0, k.length - 1);
        for (int i = 0; i < k.length; i++) {
            System.out.println(k[i]);
        }
    }
}
