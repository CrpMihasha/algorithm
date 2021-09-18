package test;

import java.nio.charset.StandardCharsets;

/**
 * 实现一个类似String的indexOf(String str)的方法，用最简单实现即可
 * 查找字符串str在目标字符串source中第一次出现的位置。
 * 不可以调用任何第三方库，也不可以用String.equals， String.substring 方法
 * 注意边界条件
 */
public class SomeClass {
    public static int indexOf(String source, String str) {
        // source为空或者str为空返回-1
        if (source == null || str == null) {
            return -1;
        }
        byte[] souceBytes = source.getBytes(StandardCharsets.UTF_8);
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);

        int ans = -1;
        if (strBytes.length > souceBytes.length) {
            // 子串大返回-1
            return ans;
        } else if (strBytes.length == souceBytes.length) {
            // souce与str长度相等，比较每个位置
            for (int i = 0; i < souceBytes.length; i++) {
                if (souceBytes[i] != strBytes[i]) {
                    return ans;
                }
            }
            return 0;
        } else {
            // souce中查找与子串第一个字节相同的值
            // 找不到返回-1
            // 找到后比较后续字节是否相等
            int souceBytesIndex = 0;
            int strBytesIndex = 0;
            boolean isBreak = false;
            for (int i = 0; i < souceBytes.length; i++) {
                souceBytesIndex = i;
                if (souceBytes[souceBytesIndex] == strBytes[strBytesIndex]) {
                    ans = souceBytesIndex;
                    while (++souceBytesIndex < souceBytes.length && ++strBytesIndex < strBytes.length) {
                        if (souceBytes[souceBytesIndex] != strBytes[strBytesIndex]) {
                            isBreak = true;
                            ans = -1;
                            break;
                        }
                    }
                    if (!isBreak) {
                        // 如果不是跳出循环，说明已经找到结果
                        return ans;
                    }
                    isBreak = false; // 重置跳出标记
                    i = souceBytesIndex; // 跳过已经比较的部分
                    strBytesIndex = 0; // 恢复子串索引
                }
            }
        }
        return ans;
    }
 
    public static void main(String[] args) {
        System.out.println("abcabdabdabdef".indexOf("abcd"));
        System.out.println(indexOf("abcabdabdabdef", "abcd"));
        if (indexOf("abcabdef", "abd") != "abcabdef".indexOf("abd")) {
            System.out.println("Oops!");
        }
    }
}
