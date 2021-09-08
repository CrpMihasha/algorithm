package test;

/**
 * 计算 1! + 2! + 3! + ... + N!
 * @Author Seth
 * @Date 2021/9/7
 */
public class Factorial {
    public static void main(String[] args) {
        factorial(10);
    }

    public static long factorial(int num) {
        long ans = 0;
        long cur = 1;
        for (int i = 1; i <= num; i++) {
            cur *= i;
            ans += cur;
        }
        System.out.println("ans = " + ans);
        return ans;
    }
}
