package heap;

import sort.SortUtil;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 大根堆
 */
public class MaxHeap {

    Integer[] heapArr;
    Integer heapSize;
    /**
     * 插入元素到数组末尾
     * 需要上浮该元素，保持大根堆的性质
     *
     * @param num 插入堆的元素
     */
    void heapInsert(Integer num){
        if (heapSize > heapArr.length - 1) {
            System.out.println("堆满了");
            return;
        }
        heapArr[heapSize] = num;
        floatUp(heapSize);
        heapSize++;
        if (heapSize > heapArr.length) {
            heapSize -= 1;
        }
    }
    public Integer max(){
        if (heapSize == 0) {
            return null;
        }

        Integer max = heapArr[0];
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
    private void floatUp(Integer index){
        // 判断输入元素的大小是否大于根节点，是则交换位置，否则结束
        if (index == 0) {
            return;
        }
        Integer rootIndex = (index - 1) >> 1;
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
    void heapify(Integer index){
        Integer left = index * 2 + 1;
        Integer right = index * 2 + 2;
        // 如果没有左子节点，结束
        if (left > heapSize - 1) {
            return;
        }
        // 如果只有左子节点，该子节点大，交换位置
        if (right > heapSize - 1 && heapArr[left] > heapArr[index]) {
            SortUtil.swap(heapArr, index, left);
        }
        // 如果左右节点都有，跟较大的交换位置，交换之后继续下沉
        if (right < heapSize) {
            Integer largestIndex = heapArr[right] > heapArr[left] ? right : left;
            SortUtil.swap(heapArr, index, largestIndex);
            heapify(largestIndex);
        }

    }

    /**
     * @param max 堆元素的最大数量
     */
    public MaxHeap(Integer max){
        heapArr = new Integer[max];
        heapSize = 0;
    }

    public Integer[] getHeapArr() {
        return heapArr;
    }

    public void setHeapArr(Integer[] heapArr) {
        this.heapArr = heapArr;
    }

    public Integer getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(Integer heapSize) {
        this.heapSize = heapSize;
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "heapArr=" + Arrays.toString(heapArr) +
                ", heapSize=" + heapSize +
                '}';
    }
}

class MaxHeapTest{
    public static void main(String[] args) {
        MaxHeapTest.batchValidate(10,1000);
//        Integer[] arr = {1, 2, 5, 4, 5, 6, 8, 8, 9, 3};
//        MaxHeap maxHeap = new MaxHeap(10);
//        for (Integer i : arr) {
//            maxHeap.heapInsert(i);
//        }
//        System.out.println(maxHeap.toString());
    }

    /**
     *
     * @param maxHeapSize 堆大小
     * @param validateCount 验证次数
     */
    public static void batchValidate(int maxHeapSize, int validateCount) {
        Random random = new Random();
        for (int i = 0; i < validateCount; i++) {
            Integer[] helpArr = new Integer[maxHeapSize*2];
            MaxHeap maxHeap = new MaxHeap(maxHeapSize);
            for (int j = 0; j < maxHeapSize; j++) {
                int num = random.nextInt(maxHeapSize);
                maxHeap.heapInsert(num);
                helpArr[j] = num;
            }
            if (!maxHeapValidate(maxHeap)) {
                SortUtil.printArrInLine(maxHeap.getHeapArr());
                SortUtil.printArrInLine(helpArr);
                throw new RuntimeException("验证失败" + maxHeap.toString());
            }
        }

    }
    /**
     * 验证思路，使用java PriorityQueue验证自定义大根堆，pop的值一致即可
     * @param maxHeap
     * @return
     */
    public static boolean maxHeapValidate(MaxHeap maxHeap){
        Integer heapSize = maxHeap.getHeapSize();

        Integer[] arr1 = new Integer[heapSize];
        int arr1Index = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Integer max = maxHeap.max();
        while ( max != null) {
            arr1[arr1Index++] = max;
            priorityQueue.add(max);
            max = maxHeap.max();
        }

        Integer[] arr2 = new Integer[heapSize];
        int arr2Index = 0;
        Integer poll = priorityQueue.poll();
        while (poll != null){
            arr2[arr2Index++] = poll;
            poll = priorityQueue.poll();
        }

        
        return Arrays.equals(arr2,arr1);
    }

}