package sort;

public class InsertSort {
    public static void sort(int[] arr) {
        int length = arr.length;
        if (arr == null || length < 2) {
            return;
        }

        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    SortUtil.swap(arr, j, j-1);
                }
            }
        }
    }
}
