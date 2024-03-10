package heap;

public class HeapUtil {
    public static void main(String[] args) {
        int[] arr = {10,9,8,7,6,5,11};
        printHeap(arr);
    }
    /**
     * todo 从上倒下逐层打印完全二叉树
     */
    public static void printHeap(int[] tree){
        int length = tree.length;
        printTree(0, tree, length);
    }

    private static void printTree(int index, int[] tree, int treeLengh){
        if (index > treeLengh - 1) {
            return;
        }
        // 打印根节点
        System.out.println(tree[index] + " ");

        // 打印左子节点
        int leftIndex = 2 * index + 1;
        if (leftIndex < treeLengh){
            System.out.print(tree[leftIndex] + " ");
        }

        // 打印右子节点
        int rightIndex = 2 * index + 2;
        if (rightIndex < treeLengh) {
            System.out.print(tree[rightIndex] + " ");
        }
        System.out.println();
        printTree(leftIndex, tree, treeLengh);
        printTree(rightIndex, tree, treeLengh);
    }
}
