package heap;

public class HeapUtil {
    public static void main(String[] args) {
        int[] arr = {10,7,8,5,6,4,3};
        printTreeBaseArr(arr);
    }
    /**
     * 逐层打印基于数组实现的完全二叉树，并使用合适的符号连接父子节点 todo  太难了
     */
    public static void printTreeBaseArr(int[] arr){
        print(arr, 0);
    }

    private static void print(int[] arr,int index) {
        int maxIndex = arr.length - 1;
        if (index > maxIndex) {
            return;
        }
        System.out.println(arr[index]);
        // 打印左节点
        int left = 2 * index + 1;
        if (left < maxIndex) {
            System.out.print(arr[left] + "  ");

        }
        // 打印右节点
        int right = 2 * index + 2;
        if (right < maxIndex) {
            System.out.print(arr[right] + "  ");
        }
        System.out.println();
        print(arr, 2 * left + 1);
        print(arr, 2 * left + 2);
        print(arr, 2 * right + 1);
        print(arr, 2 * right + 2);
    }
}
