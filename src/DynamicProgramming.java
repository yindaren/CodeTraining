import string.StringRelated;

/**
 * 假设有一个数组，它的第i个元素是一个给定的股票在第i天的价格。设计一个算法来找到最大的利润。你可以完成尽可能多的交易(多次买卖股票)。
 * 然而,你不能同时参与多个交易(你必须在再次购买前出售股票)。
 *
 * 给出一个数组样例[2,1,2,0,1], 返回 2
 *
 * 思路：使用动态规划,记住关键是从小到大开始算,数组history记录已经计算过的内容。对于[2,1,2,0,1]，
 * history[4] = maxProfit[1] = 0 , history[3] = maxProfit[0,1] = 1 ...... 直到history[0]即可
 * history[0] = maxProfit[2,1,2,0,1] = max[history[2] + prices[1] - 2, history[3] + prices[2] - 2 .....] 与 history[1]中较大者
 * 即将maxProfit[2,1,2,0,1]分成两种情况：1) 买第一个价格为2的    2)不买第一个，最大值即为history[1]
 * 注意，此题需要倒着解
 */
public class DynamicProgramming {
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0 || len ==1) {
            return 0;
        }
        int[] history = new int[len];
        //history[0] = history[1] = 0;
        for (int i = len - 2; i >= 0; i --) { //从2开始即可
            int tmpMax = 0;
            int buy = prices[i];
            for (int j = i + 1; j < len; j ++) {
                if (prices[j] <= buy) { //剪枝，非正的收益没必要计算
                    continue;
                } else {
                    int gain;
                    if (j == len - 1) {
                        gain = prices[j] - buy;
                    } else {
                        gain = prices[j] - buy + history[j + 1];
                    }
                    tmpMax = max(gain, tmpMax);
                }
            }
            history[i] = max(history[i + 1], tmpMax);
        }
        return history[0];
    }

    public static void main(StringRelated[] args) {
        int[] sample = new int[]{3,2,6,5,0,3};
        new DynamicProgramming().maxProfit(sample);
    }
}
