package sort;

public class BubbleSort {
    public static void sort(int[] arr){
        int length = arr.length;
        if (arr == null || length < 2) {
            return;
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    SortUtil.swap(arr,j,j+1);
                }
            }
        }
    }
}
