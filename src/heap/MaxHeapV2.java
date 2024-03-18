package heap;

import sort.SortUtil;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 大根堆
 */
public class MaxHeapV2 {
    private Integer[] arr;
    /**
     * 堆元素数量上限
     */
    private int limit;
    /**
     * 当前堆最后一个元素的索引，没有元素时为-1
     */
    private int lastElementIndex;

    public void heapify(int index){
        int leftIndex = 2 * index + 1;
        while (leftIndex <= lastElementIndex) {// 如果有左孩子
            // 左右节点都有，返回较大节点的索引；否则返回左孩子的索引
            int largestIndex = leftIndex + 1 <= lastElementIndex && arr[leftIndex + 1].compareTo(arr[leftIndex]) > 0 ? leftIndex + 1 : leftIndex;

            if (arr[largestIndex].compareTo(arr[index]) > 0) {
                SortUtil.swap(arr, largestIndex, index);
                index = leftIndex;
                leftIndex = 2 * index + 1;
            } else {
                break;
            }
        }
    }

    /**
     * 向堆中插入一个元素，插入后保证根节点是最大的元素
     * heapSize + 1
     * heapSize不能>limit
     * @param num
     */
    public void heapInsert(Integer num) {
        if (lastElementIndex >= limit) { // 已经达到堆元素上线
            throw new RuntimeException("已经达到堆元素上线");
        }
        arr[++lastElementIndex] = num;
        // 上浮元素
        floatUp(lastElementIndex);
    }

    /**
     * {1,3,null,4,5}
     * @param index
     */
    private void floatUp(int index) {
        while(index > 0 && arr[index].compareTo(arr[(index - 1) / 2]) > 0) {
            SortUtil.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 弹出最大的元素
     * 实现步骤：
     * 1、将最大的元素和最后一个元素交换位置
     * 2、将交换后的元素下沉
     * 3、heapSize-1
     */
    public Integer pop(){
        Integer result = null;
        if (lastElementIndex < 0) {// 堆空了
            return result;
        }
        // 将最大的元素和最后一个元素交换位置
        result = arr[0];
        SortUtil.swap(arr, 0, lastElementIndex);
        arr[lastElementIndex] = null;
        // heapSize-1
        lastElementIndex--;
        // 将交换后的元素下沉
        heapify(0);
        return result;
    }

    public MaxHeapV2(int maxHeapSize){
        arr = new Integer[maxHeapSize];
        limit = maxHeapSize;
        lastElementIndex = -1;
    }

    @Override
    public String toString() {
        return "MaxHeapV2{" +
                "arr=" + Arrays.toString(arr) +
                ", limit=" + limit +
                ", lastElementIndex=" + lastElementIndex +
                '}';
    }

    public Integer[] getArr() {
        return arr;
    }

    public void setArr(Integer[] arr) {
        this.arr = arr;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLastElementIndex() {
        return lastElementIndex;
    }

    public void setLastElementIndex(int lastElementIndex) {
        this.lastElementIndex = lastElementIndex;
    }
}

class TestMaxHeapV2 {
    public static void main(String[] args) {
        validateMaxHeapV2(10);
    }

    public static void validateMaxHeapV2(int validateCount){
        int maxHeapSize = 10;
        Random random = new Random();
        for (int i = 0; i < validateCount; i++) {
            MaxHeapV2 maxHeapV2 = new MaxHeapV2(maxHeapSize);
            PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) -> o2 - o1);
            for (int j = 0; j < maxHeapSize; j++) {
                int element = random.nextInt(maxHeapSize);
                maxHeapV2.heapInsert(element);
                queue.add(element);
            }
            for (int x = 0; x < maxHeapSize; x++) {
                Integer max = maxHeapV2.pop();
                Integer poll = queue.poll();
                if (!max.equals(poll)) {
                    System.out.println(maxHeapV2);
                    throw new RuntimeException("验证失败");
                }
            }

        }
    }

    /**
     * 验证堆size
     */
    public static void validateHeapSize(int maxHeapSize){
        MaxHeapV2 maxHeapV2 = new MaxHeapV2(maxHeapSize);
        Random random = new Random();
        for (int i = 0; i < maxHeapSize; i++) {
            int element = random.nextInt(maxHeapSize);
            maxHeapV2.heapInsert(element);
        }
        assert maxHeapV2.getLastElementIndex() == maxHeapSize - 1;
    }

    /**
     * 验证堆上限
     */
    public static void validateMaxHeapSize(int maxHeapSize){
        int exceed = maxHeapSize + 1;
        Random random = new Random();
        MaxHeapV2 maxHeapV2 = new MaxHeapV2(maxHeapSize);
        for (int i = 0; i < exceed; i++) {
            int element = random.nextInt(exceed);
            maxHeapV2.heapInsert(element);
        }
        System.out.println(maxHeapV2);
    }

    /**
     * 验证上浮操作
     * @param validateCount 验证次数
     */
    public static void validateFloatUp(int validateCount){
        int maxHeapSize = 10;
        for (int i = 0; i < validateCount; i++) {
            MaxHeapV2 maxHeapV2 = new MaxHeapV2(maxHeapSize);
            Random random = new Random();
            for (int j = 0; j < maxHeapSize; j++) {
                int element = random.nextInt(maxHeapSize);
                maxHeapV2.heapInsert(element);
            }
            // 验证堆是大根堆
            validateMaxHeap(maxHeapV2);
        }

    }

    private static void validateMaxHeap(MaxHeapV2 maxHeapV2) {
        Integer[] arr = maxHeapV2.getArr();
        int limit = maxHeapV2.getLimit();
        int parentIndex = 0;
        // 根节点大于左子节点
        int leftIndex = 2 * parentIndex + 1;
        while (leftIndex < limit) { // 右子节点未达到堆末尾
            if (arr[parentIndex].compareTo(arr[leftIndex]) >= 0) {
                parentIndex = leftIndex;
                leftIndex = 2 * parentIndex + 1;
            } else {
                System.out.println(maxHeapV2);
                throw new RuntimeException("左节点验证失败");
            }
        }

        // 根节点大于右子节点
        parentIndex = 0;
        int rightIndex = 2 * parentIndex + 2;
        while (rightIndex < limit) { // 右子节点未达到堆末尾
            if (arr[parentIndex].compareTo(arr[rightIndex]) >= 0) {
                parentIndex = rightIndex;
                rightIndex = 2 * parentIndex + 2;
            } else {
                System.out.println(maxHeapV2);
                throw new RuntimeException("右节点验证失败");
            }
        }
    }


}

