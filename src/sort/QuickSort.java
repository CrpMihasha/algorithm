package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 10, 4, 2};
        netherlandFlag2(arr, 4);
        testBatchNetherlandFlag();
    }




    /**
     * 荷兰国旗版本2
     * 给定一个数组arr，将 < key的数字放左边，=key的放在中间，将>key的数字放在右边儿
     *
     * @param arr 给定的数组
     * @param key 决定元素位置的关键数字
     * @return 返回二个位置（n,m） n为=key的最小位置 m为=key的最大位置
     */
    public static int[] netherlandFlag2(int[] arr, int key) {
        int left = -1;
        int right = arr.length;
        int[] result = new int[2];
        for (int i = 0; i < right; i++) {
            if (arr[i] > key) {
                SortUtil.swap(arr, i, --right);
                i -= 1;
            } else if (arr[i] < key) {
                SortUtil.swap(arr, i , ++left);
            }
        }
        result[0] = left;
        result[1] = right;
        return result;
    }

    public static void testNetherlandFlag2(int[]arr, int key, int[] index){
        int left = index[0];
        int right = index[1];
        try {
            // 验证左边
            for (int i = 0; i <= left; i++) {
                if (arr[i] >= key) {
                    throw new RuntimeException("左边验证失败");
                }
            }
            // 验证中间
            for (int i = left+1; i < right;i++) {
                if (arr[i] != key) {
                    throw new RuntimeException("中间验证失败");
                }
            }
            // 验证右边
            for (int i = right; i < arr.length; i++) {
                if (arr[i] <= key) {
                    throw new RuntimeException("右边验证失败");
                }
            }
        } catch (RuntimeException e) {
            System.out.print("荷兰国旗处理结果：");SortUtil.printArrInLine(arr);
            System.out.println("key = " + key);
            System.out.print("返回的位置：");SortUtil.printArrInLine(index);
            throw e;
        }
    }

    /**
     * 荷兰国旗版本1
     * 给定一个数组arr，将 <= key的数字放左边，将>key的数字放在右边儿
     *
     * @param arr 给定的数组
     * @param key 决定元素位置的关键数字
     * @return 返回二个位置[n,m) n为<=key的最大位置 m+1为>key的最小位置
     */
    public static int[] netherlandFlag1(int[] arr, int key) {
        int[] result = new int[2];
        int length = arr.length;
        // <= key的数字的边界
        int bound = -1;

        for (int i = 0; i < length; i++) {
            if (arr[i] <= key) {
                SortUtil.swap(arr, ++bound, i);
            }
        }
        result[0] = bound;
        result[1] = bound;
        return result;
    }

    public static void testBatchNetherlandFlag(){
        for (int i = 0; i < 10000; i++) {
            int[] arr = SortUtil.lenRandomValueRandom(10, 20);
            int[] copy = Arrays.copyOf(arr, arr.length);
            // 获取一个key
            int keyIndex = (int)(arr.length * Math.random());
            int key = arr[keyIndex];
            int[] result = netherlandFlag2(arr, key);
            try {
                testNetherlandFlag2(arr, key, result);
            } catch (Exception e) {
                SortUtil.printArrInLine(copy);
                throw e;
            }
        }
    }

   public static void testNetherlandFlag(int[] arr, int key, int[] index) {
        int low = index[0];
        int high = index[1];

        for(int i = 0; i <= low; i++) {
            if (arr[i] > key) {
                System.out.println("验证失败");
                System.out.print("荷兰国旗处理结果：");SortUtil.printArrInLine(arr);
                System.out.println("key = " + key);
                System.out.print("返回的位置：");SortUtil.printArrInLine(index);
                throw new RuntimeException();
            }
        }

        for (int i = high+1; i < arr.length; i++) {
            if (arr[i] <= key) {
                System.out.println("验证失败");
                System.out.print("荷兰国旗处理结果：");SortUtil.printArrInLine(arr);
                System.out.println("key = " + key);
                System.out.print("返回的位置：");SortUtil.printArrInLine(index);
                throw new RuntimeException();
            }
        }

   }

}
