package bit;

/**
 * 位运算特性：可以理解成无进位加法
 * a^a = 0 -> 任意数异或自己等于零
 * 0^a = a -> 任意数异或零等于自己
 */
public class BitDemo {

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,4,5,6,5,6};
        findRightOne(14);
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
}
