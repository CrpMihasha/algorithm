package test;

/**
 * 打印一个int类型数字的32位表示
 *
 * @Author Seth
 * @Date 2021/9/7
 */
public class Code1 {
    public static void main(String[] args) {
//        print(4);
//        System.out.println();
//        int a = 100;
//        int b = 50;
//        print(a);
//        System.out.println();
//        print(b);
//        System.out.println();
//        print(a | b);
//        System.out.println();
//        print(a & b);
//        System.out.println();
//        print(a ^ b);
//        print(a ~ b);
        // 取相反数
        int c = 10;
        System.out.println(c);
        System.out.println(~c + 1);

    }
    public static void print(int num){
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
    }
}
