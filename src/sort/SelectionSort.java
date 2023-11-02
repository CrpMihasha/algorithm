package sort;

public class SelectionSort {
    public static void sort(int[] arr) {
        int length = arr.length;
        if (arr == null || length < 2) {
            return;
        }

        int minIndex;
        for (int i = 0; i < length; i++) {
            minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            SortUtil.swap(arr, minIndex, i);
        }

    }
}
