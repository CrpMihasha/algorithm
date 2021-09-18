package test;

/**
 * 使用位运算实现+-*|
 *
 * @Author Seth
 * @Date 2021/9/18
 */
public class MathMethod {
    public static void main(String[] args) {
        System.out.println(divide(24, 12));
    }

    /**
     * 打印一个整型数字的二进制形式
     */
    public static void printBinary(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? 0 : 1);
        }
        System.out.println();
    }

    /**
     * 思路： a + b = a ^ b + (a & b) << 1，因为不能使用加法，所以我们需要让第二个加数即(a & b) << 1 == 0,然后返回a ^ b即可
     * 1、 a ^ b 相当于a与b无进位相加
     * a = 1101,
     * b = 0110
     * a ^ b = 1011 (异或运算相同得0不同得1)
     *
     * 2、(a & b) << 1 得到 a + b 的进位信息
     *
     * @param a 被加数
     * @param b 加数
     * @return 和
     */
    public static int add(int a, int b){
        int temp;
        while (b != 0) {
            temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }

    /**
     * a - b 等同于 a + (-b), 因为不能使用四则运算符号，
     * 所以我们取b的相反数即可 -b = ~b + 1
     * @param a 被减数
     * @param b 减数
     * @return 差
     */
    public static int minus(int a, int b){
        return add(a, add(~b, 1));
    }

    /**
     * a = 1101,
     * b = 0101
     * 利用小学乘法
     *          1101
     *          0101
     *        ---------
     *          1101
     *         0000         把过程结果相加即可
     *        1101
     *       0000
     *      ----------
     *       1000001
     *
     * @param a 被乘数
     * @param b 乘数
     * @return 积
     */
    public static int multi(int a, int b){
        int cur = 0;
        int bit;
        int ans = 0;
        while (b >= (bit = 1 << cur)) {
            int temp = b & bit;
            if (temp != 0) {
                a <<= cur;
                ans = add(ans, a);
            }
            cur++;
        }
        return ans;
    }

    /**
     * 字节： 0 0 0 0 0 0 0 0 0
     * 位置： 8 7 6 5 4 3 2 1 0
     *
     * 实现除法需要注意参数的正负情况，因为涉及到参数右移运算，如果将符号位一起移动会导致程序无法执行完成
     * 将参数转成其绝对值的形式再进行运算
     * a = 13,二进制表示为1101
     * b = 5, 二进制表示为0101
     * 1、将除数b << 1 变成1010,发现比a小；
     * 2、将a << 2，变成10100，发现比a大,
     * 3、所以在1这个位置上一定有一个结果，即从右向左的第二个位置上有一个1,
     * 4、a - (b << 1) 得到一个新的被除数11
     * 5、发现被除数小于除数b，运算结果，得到结果二进制为：10 即 2
     *
     * @param a 被除数
     * @param b 除数
     * @return 商（向下取整）
     */
    public static int divide(int a, int b){
        boolean isAllNeg = isNegtive(a) || isNegtive(b);
        if (isNegtive(a))
            a = add(~a, 1);
        if (isNegtive(b))
            b = add(~b, 1);
        int ans = 0;
        int temp;
        if (a < b) {
            return 0;
        } else if (a == b) {
            return 1;
        } else {
            for (int i = 31; i >= 0; i--) {
                temp = a >>> i;
                if (temp >= b) {
                    ans += (1 << i);
                    a -= (b << i);
                }
            }
        }
        return isAllNeg ? add(~ans, 1) : ans;
    }

    public static boolean isNegtive(int num) {
        return num < 0;
    }

}
