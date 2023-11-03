package find;

import sort.Sort;
import sort.SortUtil;

/**
 * @Author Seth
 * @Date 2021/9/9
 */
public class HalfFind {
    public static void main(String[] args) throws Exception{
        int testTimes = 100000;
        int[] arr1 = {1 ,3, 3, 3, 4, 4, 5, 5 };
        for (int i = 0; i < testTimes; i++) {
            int[] arr = SortUtil.lenRandomValueRandom(10, 6);
            Sort.bubbleSort(arr);
            int validate = validateFindMostLeftNum(arr1, 5);
            int mostLeftNum = findMostLeftNum(arr1, 5);
            if (validate != mostLeftNum) {
                System.out.println("validate = " + validate);
                System.out.println("mostLeftNum = " + mostLeftNum);
                System.out.println("出错了");
                SortUtil.printArrInLine(arr);
                break;
            }
        }

//        System.out.println(findMostLeftNum(arr, 5));
//        System.out.println(validateFindMostLeftNum(arr, 5));
    }

    public static int findFirstLeftIndex(int[] arr, int target){
        if (arr == null) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int L = 0, R = arr.length-1,mid,resultIndex = -1;
        while (L < R) {
            mid = L + (R-L) >> 1;
            if (arr[mid] > target) {
                R = mid - 1;
            } else if (arr[mid] == target) {
                resultIndex = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return resultIndex;
    }

    /**
     * 有序数组中找到>=num最左的位置
     */
    public static int findMostLeftNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int ans = -1;

        int L = 0;
        int R = arr.length - 1;

        while (L <= R) {
            int mid = (L + R) >>> 1;
            if (arr[mid] < num) {
                L = mid + 1;
            } else if (arr[mid] >= num) {
                R = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }
    public static int validateFindMostLeftNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 有序数组中找到num
     */
    public static int find(int[] arr, int num) {
        // 处理边界
        int ans = -1;
        if (arr == null || arr.length == 0) {
            return ans;
        }

        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = (L + R) >>> 1;
            if (arr[mid] > num) {
                R = mid - 1;
            } else if (arr[mid] < num){
                L = mid + 1;
            } else {
                return mid; // 找到目标值以后return，不然会死循环
            }
        }
        return ans;
    }
    /**
     * 包里验证find
     */
    public static int validateFind(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }

}
