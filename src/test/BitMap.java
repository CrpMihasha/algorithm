package test;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;

/**
 * 这个位图用来记录出现过的任何数字
 *
 * @Author Seth
 * @Date 2021/9/14
 */
public class BitMap {
    public static void main(String[] args) {
        System.out.println("test begin");
        BitMap bitMap = new BitMap(6400);
        HashSet<Integer> set = new HashSet<>(6400);
        for (int i = 0; i < 1000; i++) {
            int value = (int) (Math.random() * 6400); // [0, 6400)
            bitMap.add(value);
            set.add(value);
        }
        for (Integer integer : set) {
            if (!bitMap.exist(integer)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
    /**
     * 每个位置可以记录64数字
     * long类型为8字节，每字节8个bit，一个bit可以记录一个出现过的数字
     * arr[0]，可以记录0~63
     * arr[1]，可以记录63~127
     * 。。。
     * arr[length-1],可以记录（length-1）*63~（length-1）*63+64
     */
    long[] arr;

    /**
     * 构造一个位图，参数为这个位图中可能用于存储的最大值
     */
    public BitMap(long maxValue) {
        arr = new long[(int)((maxValue + 64)/64)];
    }

    public void add(long value){
        // 首先判断这个值由谁记录
        int index = (int)(value / 64);
        // 判断这个值在元素的哪个位置上, value % 64 等同于 value & 63
        long bit = (value & 63);
        arr[index] |= 1 << bit;
    }

    public boolean exist(long value) {
        // 首先判断这个值由谁记录
        int index = (int)(value / 64);
        // 判断这个值在元素的哪个位置上, value % 64 等同于 value & 63
        long bit = (value & 63);
        long l = arr[index] & (1 << bit);
        return l == 0l ? false : true;
    }

    public void remove(long value){
        // 首先判断这个值由谁记录
        int index = (int)(value / 64);
        // 判断这个值在元素的哪个位置上, value % 64 等同于 value & 63
        long bit = (value & 63);
        arr[index] &= 0 << bit;
    }
}
