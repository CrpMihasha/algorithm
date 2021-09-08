package trick;

import sort.SortUtil;

/**
 * 题目：从1~5随机改成1~7随机
 * 给定一个函数f(),这个函数可以等概率的生成一个在1~5之间的随机数。
 * 要求：利用函数f()写出一个函数g(),g()可以等概率的生成一个在1~7之间的随机数。不允许修改函数f()
 *
 * @Author Seth
 * @Date 2021/9/8
 */
public class Code01_Random {
    public static void main(String[] args) {
        int testTimes = 100000;
        int[] count = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int elem = g();
            count[elem]++;
        }
        print(count);
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + " 出现了" + arr[i] + " 次");
        }
    }

    /**
     * 生成一个[1,5]的随机数
     */
    public static int f(){
        int ans = 0;
        do {
            ans = (int)(Math.random() * 6);
        } while (ans == 0);
        return ans;
    }

    /**
     * 等概率生成[1,7]之间的随机数
     */
    public static int g(){
        int ans = 0;
        do {
            ans = randomZero2Seven();
        } while (ans == 0);
        return ans;
    }

    /**
     * 等概率生成0~7之间的随机数
     */
    public static int randomZero2Seven(){
        return (random01() << 2) + (random01() << 1) + random01();
    }

    /**
     * 等概率生成0或者1
     */
    public static int random01(){
        int ans = 0;
        do {
            ans = f();
        } while (ans == 3);

        return ans < 3 ? 0 : 1;
    }

}
