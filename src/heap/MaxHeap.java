package heap;

import sort.SortUtil;

/**
 * 大根堆
 */
public class MaxHeap {
    // todo 写对数器，验证大根堆
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.heapInsert(1);
        maxHeap.heapInsert(2);
        maxHeap.heapInsert(3);
        SortUtil.printArrInLine(maxHeap.getHeapArr());
        System.out.println("堆大小" + maxHeap.getHeapSize());
        System.out.println("===============================================");
        int max = maxHeap.max();
        System.out.println("max = " + max);
        SortUtil.printArrInLine(maxHeap.getHeapArr());
        System.out.println("堆大小" + maxHeap.getHeapSize());
        System.out.println("===============================================");
        maxHeap.heapInsert(7);
        maxHeap.heapInsert(9);
        maxHeap.heapInsert(8);
        SortUtil.printArrInLine(maxHeap.getHeapArr());
        System.out.println("堆大小" + maxHeap.getHeapSize());
        int max1 = maxHeap.max();
        System.out.println("max = " + max1);
        SortUtil.printArrInLine(maxHeap.getHeapArr());
        System.out.println("堆大小" + maxHeap.getHeapSize());
        System.out.println("===============================================");
    }
    int[] heapArr;
    int heapSize;
    /**
     * 插入元素到数组末尾
     * 需要上浮该元素，保持大根堆的性质
     *
     * @param num 插入堆的元素
     */
    void heapInsert(int num){
        heapArr[heapSize] = num;
        floatUp(heapSize);
        heapSize++;
    }
    public int max(){
        int max = heapArr[0];
        // 堆首元素和堆尾元素交换位置
        SortUtil.swap(heapArr, 0, heapSize - 1);
        // 堆大小-1
        heapSize -= 1;
        // 堆首元素下沉
        heapify(0);
        return max;
    }

    /**
     *
     *
     * @param index 需要上浮元素的索引
     */
    private void floatUp(int index){
        // 判断输入元素的大小是否大于根节点，是则交换位置，否则结束
        if (index == 0) {
            return;
        }
        int rootIndex = (index - 1) >> 1;
        while (heapArr[index] > heapArr[rootIndex]) {
            SortUtil.swap(heapArr, index, rootIndex);
            index = rootIndex;
            if (index == 0) { // 如果已经到数组第一个元素，就不再需要继续寻找根节点
                return;
            }
            rootIndex = (index - 1) >> 1;
        }
    }

    /**
     * 在计算机科学中，heapify 是将一个数组或者一个区间变成一个堆的操作，是堆排序和优先队列的基础操作之一。堆化可以分为两种：自上而下的堆化和自下而上的堆化。
     * 判断以当前位置为根节点的元素是否最大，不是则要下沉，跟子节点中较大的元素交换位置
     */
    void heapify(int index){
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        // 如果没有左子节点，结束
        if (left > heapSize - 1) {
            return;
        }
        // 如果只有左子节点，该子节点大，交换位置
        if (right > heapSize - 1 && heapArr[left] > heapArr[index]) {
            SortUtil.swap(heapArr, index, left);
        }
        // 如果左右节点都有，跟较大的交换位置，交换之后继续下沉
        if (right < heapSize - 1) {
            int largestIndex = heapArr[right] > heapArr[left] ? right : left;
            SortUtil.swap(heapArr, index, largestIndex);
            heapify(largestIndex);
        }

    }

    /**
     * @param max 堆元素的最大数量
     */
    public MaxHeap(int max){
        heapArr = new int[max];
        heapSize = 0;
    }

    public int[] getHeapArr() {
        return heapArr;
    }

    public void setHeapArr(int[] heapArr) {
        this.heapArr = heapArr;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }
}

class MaxHeapTest{
    public static boolean maxHeapValidate(MaxHeap maxHeap){
        int[] heapArr = maxHeap.getHeapArr();
        int heapSize = maxHeap.getHeapSize();

        doValidate(heapArr,0, heapSize);
        return true;
    }

    private static void doValidate(int[] heapArr, int rootIndex, int heapSize) {
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;
       if (rootIndex > heapSize - 1){ // 已经是最后一个元素了 终止验证
           return;
       }
       if (right > heapSize - 1 && heapArr[left] > heapArr[rootIndex]) {// 只有左子节点，验证完毕即结束
           throw new RuntimeException("非最大堆，左子节点索引： " + left + ", 根索引： " + rootIndex);
       }
       if (right < heapSize - 1) { // 左右节点都有，继续递归验证
           if (heapArr[left] > heapArr[rootIndex] || heapArr[right] > heapArr[rootIndex]){ //任何一个子节点大于根节点，都验证失败
               throw new RuntimeException("非最大堆，左子节点索引： " + left + ", 右子节点索引： " + right + ", 根索引： " + rootIndex);
           }
           doValidate(heapArr, left, heapSize); //验证左子树
           doValidate(heapArr, right, heapSize); //验证右子树
       }
    }
}