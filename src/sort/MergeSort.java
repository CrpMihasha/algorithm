package sort;

public class MergeSort {
    public static void sort(int[] arr) {
        sort(arr, 0 ,arr.length - 1);
    }
    private static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + ((high -  low) >> 1);
            sort(arr, low, mid);
            sort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] tmp = new int[high-low+1];
        int index = 0;
        // 合并low mid
        // 合并mid+1 high
        for (int i = low, j = mid + 1;;){
            if (arr[i] < arr[j]) {
                tmp[index++] = arr[i];
                i++;
            } else if (arr[i] >= arr[j]){
                tmp[index++] = arr[j];
                j++;
            }

            if (i > mid) {
                // 左边结束
                for(int leftI = j; j <= high; j++){
                    tmp[index++] = arr[leftI];
                }
                break;
            }
            if (j > high) {
                // 右边结束
                for(int rightI = i; i <= mid; i++){
                    tmp[index++] = arr[rightI];
                }
                break;
            }
        }
        // 复制临时数组的元素回原数组
        for (int i = 0; i < tmp.length; i++) {
            arr[low++] = tmp[i];
        }
    }
}
