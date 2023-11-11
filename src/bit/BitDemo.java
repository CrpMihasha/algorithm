package bit;


import java.util.HashMap;
import java.util.HashSet;

/**
 * 位运算特性：可以理解成无进位加法
 * a^a = 0 -> 任意数异或自己等于零
 * 0^a = a -> 任意数异或零等于自己
 */
public class BitDemo {

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,4,5,6,5,6};
//        findRightOne(14);
        Integer num1 = -1;
        Integer num2 = 1;
        System.out.println(Integer.toBinaryString(num1));
        System.out.println(Integer.toBinaryString(~num1));
        System.out.println(Integer.toBinaryString(num2));
        System.out.println(Integer.toBinaryString(~num2));
        System.out.println(Integer.toBinaryString(0));
    }

    /**
     * 给定一个数，找出最右的1
     * 例子：
     * int a = 14, 二进制标识为：000001110， 则结果为000000010 暨 2
     *
     * 计算过程：
     * ~a = 111110001
     * ~a + 1 = 111110010
     * a & (~a+1) = 000001110
     *            & 111110010
     *         -------------------
     *              000000010
     *  有因为：~a+1 = -a
     *  故答案可写成： a & (-a)
     */
    public static void findRightOne(int a) {
        int i1 = a & (~a+1);
        int i2 = a & (-a);
        System.out.println(i1);
        System.out.println(i2);
    }

    /**
     * 给定一个数组，其中的数都出现了偶数次，只有一个数出现了奇数次，找出这个数并打印
     */
    public static void findOdd(int[] arr) {
        int eor = 0;
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                eor ^= arr[i];
            }
        }
        System.out.println(eor);
    }


    /**
     * 使用异或交换2个数的注意事项：
     *  由于任意数异或自己等于0，因为如果交换的数在同一块内存区域，结果为0
     */
    public static void exchange(int a, int b) {
        System.out.println("交换前： " + a + ", " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("交换后： " + a + ", " + b);
    }

    /**
     * 给定一个数组arr, 正整数k,m,其中k < m, m >0
     * 有一个数出现了k次，其他数字都出现了m次，找出并打印出现了k次的数
     */
    public static int findKTimesInteger(int[] arr, int k, int m){
        // 这是一个int数组，将数组中每个整数的二进制位相加。
        // 将每个相加的结果与m取模，模数=0则证明出现了k次的数，在这个二进制位是0
        int[] sumArr = new int[32];

        int length = arr.length;

        // 得到所有数的二进制累加和
        for (int i = 0; i < length; i++) {
            int num = arr[i];
            for (int j = 0; j < 32; j++) {
                sumArr[j] += (num & (1<<j));
            }
        }

        int ans = 0;
        for (int i = 0; i < sumArr.length; i++) {
            // 对m取模为0，证明这个位置是0
            if (sumArr[i] % m != 0) {
                ans += (1 << i);
            }
        }
        System.out.println(ans);
        return ans;
    }

    // 验证findKTimesInteger的对数器
    public static int validater(int[] arr, int k, int m){
        HashMap<Integer, Integer> map = new HashMap(arr.length);
        for (int i = 0; i < arr.length; i++) {
            Integer value = map.get(arr[i]);
            map.put(arr[i], value == null ? 0 : value+1);
        }

        for (Integer integer : map.keySet()) {
            Integer count = map.get(integer);
            if (count == k) {
                return integer;
            }
        }
        throw new RuntimeException("没找到这个数");
    }

    /**
     *
     * @param size 生成一个size大小的数组
     * @param range 元素范围在(-range, range)
     */
    public static int[] generateIntArr(int size, int range){}

}
