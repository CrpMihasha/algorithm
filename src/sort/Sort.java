package sort;

/**
 * @Author Seth
 * @Date 2021/9/7
 */
public class Sort {
    public static void main(String[] args) {
        int[] arr = {2,1,6,7,4,8,0};
        SortUtil.printArr(arr);
        insertionSort(arr);
        SortUtil.printArr(arr);
    }

    /**
     * 插入排序
     * 给定一个数组arr,长度为N（N > 2）,整体思路是先保证
     * 索引0~索引0位置有序、
     * 索引0~索引1位置有序、
     * 索引0~索引2位置有序、
     * 索引0~索引3位置有序、
     * ...
     * 索引0~索引N-1位置有序
     *
     * 操作步骤:
     * 1、保证索引0~索引0位置有序，相同位置一定有序，不需要操作
     * 2、索引0~索引1位置有序，比较arr[0],arr[1],如果arr[1] < arr[0]，交换arr[0]和arr[1]的位置；
     * 3、索引0~索引2位置有序，比较arr[1],arr[2],如果arr[2] < arr[1]，交换arr[1]和arr[2]的位置；比较arr[0],arr[1],如果arr[1] < arr[0]，交换arr[0]和arr[1]的位置；
     * 4、索引0~索引3位置有序，比较arr[2],arr[3],如果arr[3] < arr[2]，交换arr[2]和arr[3]的位置；比较arr[1],arr[2],如果arr[2] < arr[1]，交换arr[1]和arr[2]的位置；比较arr[0],arr[1],如果arr[1] < arr[0]，交换arr[0]和arr[1]的位置；
     * ...
     * N、索引0~索引N-1位置有序，比较arr[N-2],arr[N-1],如果arr[N-1] < arr[N-2]，交换arr[N-1]和arr[N-2]的位置；比较arr[N-3],arr[N-2],如果arr[N-2] < arr[N-3]，交换arr[N-2]和arr[N-3]的位置.....比较arr[0],arr[1],如果arr[1] < arr[0]，交换arr[0]和arr[1]的位置；
     *
     * @param arr 待排序数组
     */
    public static void insertionSort(int[] arr){
        // 先处理边界
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;

        for (int i = 0; i < N; i++) {
            for (int j = i; j - 1 >= 0 && arr[j] < arr[j-1]; j--) {
                SortUtil.swap(arr, j, j - 1);
            }
        }
    }


    /**
     * 冒泡排序
     * 倒一杯水在玻璃杯里，我们都看见过，杯中的水会有泡泡向上浮起。冒泡排序的过程跟这个过程相似，
     * 给定一个数组arr，相邻索引之间两两比较，将更大的数字向后移动，这样第一次比较之后数组长度N-1的位置一定是最大的数字；
     * 再两两比较，到数组N-2的位置，这样N-2一定是第二大的数字，重复这个步骤即可完成冒泡排序。
     *
     * 操作步骤:
     * 1. 比较索引0，1位置的数字，如果arr[0]>arr[1},交换arr[0]和arr[1]的位置,否则不交换位置；比较索引1,2位置的数字，如果arr[1]>arr[2]，交换arr[1]和arr[2]，否则不交换位置；比较arr[N-2]和arr[N-1]...
     * 2. 比较索引0，1位置的数字，如果arr[0]>arr[1},交换arr[0]和arr[1]的位置,否则不交换位置；比较索引1,2位置的数字，如果arr[1]>arr[2]，交换arr[1]和arr[2]，否则不交换位置；比较arr[N-3]和arr[N-2]...
     * 3. 比较索引0，1位置的数字，如果arr[0]>arr[1},交换arr[0]和arr[1]的位置,否则不交换位置；比较索引1,2位置的数字，如果arr[1]>arr[2]，交换arr[1]和arr[2]，否则不交换位置；比较arr[N-4]和arr[N-3]...
     *
     * @param arr 待排序数组
     */
    public static void bubbleSort(int[] arr) {
        // 先处理边界
        if (arr == null || arr.length <2) {
            return;
        }

        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortUtil.swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     * 给定一个数组，使用选择排序时，先假定第一个位置是最小值，依次向后查找，找到更小的数就更新最小索引，遍历完成交换第一个位置和最小索引数；再假定第二个位置是最小值，
     * 依次向后查找，找到更小的数就更新最小索引，遍历完成交换第二个位置和最小索引数;重复这个步骤，最后假定数组长度N-2的位置是最小值，与N-1的位置比较，如果后面的数更小
     * 就交换位置。这样整个数组就排序完成了。
     *
     * 操作步骤：
     * 1、假定最小数索引minValueIndex = 0,依次比较索引1,2,3...N, 如果找到更小的数，更新最小索引minValueIndex，交换索引0和minValueIndex的数字
     * 2、假定最小数索引minValueIndex = 1,依次比较索引2,3...N, 如果找到更小的数，更新最小索引minValueIndex，交换索引1和minValueIndex的数字
     * 3、假定最小数索引minValueIndex = 2,依次比较索引2,3...N, 如果找到更小的数，更新最小索引minValueIndex,交换索引2和minValueIndex的数字
     * .
     * .
     * .
     * N、假定最小数索引minValueIndex = N-1,依次比较索引N-1,N, 如果找到更小的数，更新最小索引minValueIndex,交换索引N-1和minValueIndex的数字
     * @param arr 待排序数组
     */
    public static void selectionSort(int[] arr) {
        // 先处理边界
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[minValueIndex] > arr[j]) {
                    minValueIndex = j;
                }
            }
            SortUtil.swap(arr, i, minValueIndex);
        }

    }
}
