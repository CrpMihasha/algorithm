package sort;

/**
 * @Author Seth
 * @Date 2021/9/7
 */
public class SortUtil {
    /**
     * 打印数组
     *
     * @param arr 数组
     */
    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
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
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
