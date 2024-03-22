package leetcode.array;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= Math.pow(10,5)
 * 0 <= prices[i] <= Math.pow(10,4)
 */
public class T121 {
    /**
     * 解法1
     * 最笨的解法
     * 假设第1天买，第2，3...n天卖，得到最大利润
     * 假设第2天买，第3，4...n天卖，得到最大利润
     */
    public int maxProfit(int[] prices) {
        int maxProfix = 0;
        int length = prices.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++){
                maxProfix = Math.max(maxProfix, prices[j] - prices[i]);
            }
        }
        return maxProfix;
    }

    /**
     * 解法2：
     * 遍历数组，过程中记住最小值，用当前遍历到的元素-最小值即可
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int length = prices.length;
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < length; i++) {
            min = Math.min(min, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }
        return maxProfit;
    }
}
