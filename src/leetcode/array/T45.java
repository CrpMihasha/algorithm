package leetcode.array;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 *
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 10的4次方
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达 nums[n-1]
 */
public class T45 {
    public static void main(String[] args) {
        int[] arr = {2,3,0,1,2};
        int jump = new T45().jump1(arr);
        System.out.println(jump);
    }
    public int jump(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        int left = 0,tempLeft = 0,right = nums[0],max = nums[0], jump = 1;
        if (max >= length - 1) {
            return 1;
        }
        while (true){
            while (left <= right) {
                if (max < left + nums[left]){
                    max = left + nums[left];
                    tempLeft = left;
                } else {}
                left++;
            }
            jump++;
            if (max >= length - 1) {
                break;
            }
            left = tempLeft;
            right = max;
        }
        return jump;
    }

    /**
     *
     作者：力扣官方题解
     链接：https://leetcode.cn/problems/jump-game-ii/solutions/230241/tiao-yue-you-xi-ii-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

}
