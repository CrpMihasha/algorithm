package test;

import java.nio.charset.StandardCharsets;

/**
 * 这个位图用来记录出现过的任何数字
 *
 * @Author Seth
 * @Date 2021/9/14
 */
public class BitMap {
    public static void main(String[] args) {
        String s = "abc你";
        System.out.println();
    }
    /**
     * 每个位置可以记录64数字
     * long类型为8字节，每字节8个bit，一个bit可以记录一个出现过的数字
     * index=0，可以记录0~63
     * index=1，可以记录63~127
     * 。。。
     * index=length-1,可以记录（length-1）*63~（length-1）*63+64
     */
    long[] arr;

    /**
     * 构造一个位图，参数为这个位图中可能用于存储的最大值
     */
    public BitMap(long maxValue) {
        arr = new long[(int)(maxValue/64)];
    }

    public void add(long value){
        // 首先判断这个值
    }
}
