package heap;

/**
 * 二叉堆，数组实现
 * 索引：[0,1,2,3,4,5,6,7,8,9,10]
 * 0位置索引不用，从索引1开始
 * 左节点= root节点索引*2
 * 右节点=root节点索引*2 + 1
 *
 * 最大堆：root节点 > 左右子节点
 * 最小堆：root节点 < 左右子节点
 *
 * 使用二叉堆实现优先级队列
 */
public class MaxPR<Integer> {
    private Integer[] arr;
    private int size = 0;
    public int size(){
        return size;
    }
    public Integer max(){
        return arr[1];
    }

    // 插入到数数组末尾
    public void insert(){}

    // 上浮
    public void swim(int index){
//        while()
    }

    // 下沉
    public void sink(int index){


    }

}
