package test;

/**
 * 打印一个int类型数字的32位表示
 *
 * @Author Seth
 * @Date 2021/9/7
 */
public class Code1 {
    public static void main(String[] args) {
        // 1111111111
        // 10000
        System.out.println(16 & 16);
    }
    public static void print(int num){
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }
}
