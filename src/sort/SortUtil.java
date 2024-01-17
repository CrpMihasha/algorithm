package sort;

/**
 * @Author Seth
 * @Date 2021/9/7
 */
public class SortUtil {
    public static void main(String[] args) {
        SortUtil.sortTest(100);
    }
    /**
     * 验证方法
     */
    public static void sortTest(int loopCount) {
        for (int i = 0; i < loopCount; i++) {
            int[] arr = lenRandomValueRandom(20, 100);
//            Sort.quickSort(arr, 0, arr.length - 1);
            MergeSort.sort(arr);
            if (!isSorted(arr)) {
                printArrInLine(arr);
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("New Bee!");
    }

    /**
     * 验证一个给定的数组是否为升序排列
     *
     * @param arr int数组
     * @return 如果数组有序，返回true，否则返回false
     */
    public static boolean isSorted(int[] arr) {
        // 边界处理
        if (arr == null || arr.length < 2) {
            return true;
        }

        int N = arr.length;
        int maxValue = arr[0];
        for (int i = 0; i < N; i++) {
            if (arr[i] >= maxValue) {
                maxValue = arr[i];
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 拷贝数组
     *
     * @param arr 要拷贝的int数组
     * @return 拷贝结果
     */
    public static int[] copyArr(int[] arr) throws Exception {
        // 处理边界
        if (arr == null) {
            System.out.println("非法参数，数组不能为null。");
            throw new Exception();
        }

        int[] ans = new int[arr.length];
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    /**
     * 生成一个长度随机，值随机的数组。
     * 对于给定的maxLen，所生成数组的长度在[0,maxLen-1]之间
     * 对于给定的maxValue，所生成数组元素的值在[0,maxValue-1]之间
     *
     * @param maxLen 指定数组最大长度
     * @param maxValue 指定数组最大值
     * @return 长度随机值随机的数组
     */
    public static int[] lenRandomValueRandom(int maxLen, int maxValue){
        // 边界处理
        if (maxLen <= 0) {
            System.out.println("maxLen必须为非0的正整数");
            throw new RuntimeException();
        }
        if (maxValue <=0) {
            System.out.println("maxValue必须为非0的正整数");
            throw new RuntimeException();
        }

        int len = (int)(Math.random() * maxLen ) + 1;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int)(Math.random() * maxValue);
        }
        return ans;
    }
    /**
     * 打印数组
     *
     * @param arr 数组
     */
    public static void printArrInLine(int[] arr){
        if (arr == null || arr.length == 0) {
            System.out.println("要打印的数组不能为null或者长度为0");
            return;
        }
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("}");
        System.out.println();
    }

    /**
     * 打印数组
     *
     * @param arr 数组
     */
    public static void printArrOffLine(int[] arr){
        if (arr == null || arr.length == 0) {
            System.out.println("要打印的数组不能为null或者长度为0");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println();
    }

    /**
     * 交换数组中索引为i, j的数字的位置
     *
     * @param arr 数组
     * @param i 要交换位置的索引
     * @param j 要交换位置的索引
     */
    public static void swap(int[] arr, int i, int j) {
        if (arr == null || i < 0 || j < 0 || i == j) {
            return;
        }

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
