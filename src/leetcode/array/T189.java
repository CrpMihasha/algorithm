package leetcode.array;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *
 *
 * 进阶：
 *
 * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 */
public class T189 {
    /**
     * 其中一种思路是基于反转数组（或其中的一部分）来获得所需的结果。想一想通过使用示例，反转可能如何潜在地帮助我们。
     */
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        if(length < 2){
            return;
        }
        for (int i = 0; i < length; i++) {
            //
        }
    }

    /**
     * 一步一步的移动
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k){
        k = k % nums.length;
        // 一步一步的移动
        for (int i = 0; i < k; i++) {
            int length = nums.length;
            int last = nums[length-1];
            int current = nums[0];
            for (int j = 1; j < length; j++) {
                int temp = nums[j];
                nums[j] = current;
                current = temp;
            }
            nums[0] = last;
        }
    }

    /**
     * 思路1
     * 先reverse数组，在交换左右子数组中的元素位置
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k){
        // reverse数组
        int head = 0;
        int tail = nums.length - 1;
        while (head < tail) {
            int temp = nums[head];
            nums[head++] = nums[tail];
            nums[tail--] = temp;
        }
        // 以k-1为界分隔2个数组，分别交换2个子数组的折叠位
        // 交换左子数组
        head = 0;
        tail = k - 1;
        while (head < tail) {
            int temp = nums[head];
            nums[head++] = nums[tail];
            nums[tail--] = temp;
        }
        // 交换右子数组
        head = k;
        tail = nums.length - 1;
        while (head < tail) {
            int temp = nums[head];
            nums[head++] = nums[tail];
            nums[tail--] = temp;
        }
    }
}
