package tree;

import java.util.LinkedList;
import java.util.Objects;

public class TreeUtils<T> {
    /**
     * 给定一个二叉树的头节点，判断其是否为二叉搜索树
     * 根据题意，满足以下条件的二叉树是搜索二叉树
     * 1、X的左子树是搜索二叉树
     * 2、X的右子树是搜索二叉树
     * 3、X的左子树的最大值小于X
     * 4、X的右子树的最小值大于X
     */
    public static boolean isBST(TreeNode head){

    }

    private static BSTInfo bstProcess(TreeNode<Integer> node){
        // 写递归必须先想base case，就是这个递归什么时候结束
        if (node == null){
            return null;
        }
        BSTInfo leftInfo = bstProcess(node.left);
        BSTInfo rightInfo = bstProcess(node.right);

        boolean isBST = true;
        Integer max = node.value;
        Integer min = node.value;

        // X的左子树是搜索二叉树,X的右子树是搜索二叉树
        if (Objects.nonNull(leftInfo) && !leftInfo.isBST){
            isBST = false;
        }
        if (Objects.nonNull(rightInfo) && !rightInfo.isBST){
            isBST = false;
        }

        // X的左子树的最大值小于X，X的右子树的最小值大于X
        if (Objects.nonNull(leftInfo)){
            max = Math.max(max, leftInfo.max);
        }
        if (Objects.nonNull(rightInfo)){
            min = Math.min(max, rightInfo.min);
        }
        if (max >= node.value){
            isBST = false;
        }
        if (min <= node.value){
            isBST = false;
        }

        return new BSTInfo(isBST, max, min);
    }

    static class BSTInfo {
        boolean isBST;
        int max;
        int min;

        public BSTInfo(boolean isBST, int max, int min){
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    /**
     * 判断一个二叉树是否是平衡树
     * 平衡树的定义： 最大高度和最小高度差<=1
     *
     * @param
     * @return
     */
    public static boolean isBalanced(TreeNode head){
        return process(head).isBalanced;
    }
    private static BalanceInfo process(TreeNode x){
        if (x == null){
            return new BalanceInfo(true, 0);
        }
        BalanceInfo leftInfo = process(x.left);
        BalanceInfo rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;

        if (!leftInfo.isBalanced){
            isBalanced = false;
        }
        if (!rightInfo.isBalanced){
            isBalanced = false;
        }
        if (Math.abs(leftInfo.height - rightInfo.height) > 1){
            isBalanced = false;
        }
        return new BalanceInfo(isBalanced, height);
    }
    public static class BalanceInfo {
        public boolean isBalanced;
        public int height;

        public BalanceInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    /**
     * 判断二叉树是否是完全二叉树
     */
   public static boolean isCBT1(TreeNode head){
       if (head == null){
           return true;
       }

       LinkedList<TreeNode> queue = new LinkedList<>();
       //是否遇到过左右两个孩子不双全的节点
       boolean leaf = false;
       TreeNode l = null;
       TreeNode r = null;
       queue.add(head);
       while (!queue.isEmpty()){
           head = queue.poll();
           l = head.left;
           r = head.right;
           //如果遇到了不双全的节点之后，又发现当前节点不是叶节点
           if ((leaf && (l != null || r != null)) || (l == null && r!= null)){
               return false;
           }
           if (l != null){
               queue.add(l);
           }
           if (r != null){
               queue.add(r);
           }
           if (l == null || r ==null){
               leaf = true;
           }
       }
       return true;
   }



    /**
     * 递归方式
     * 使用数组生成一个二叉树，这个数组是一个堆结构
     */
    public TreeNode buildBinaryTree(Integer[] heapArray) {
        if (heapArray == null || heapArray.length == 0) {
            return null;
        }
        return buildBinaryTree(heapArray, 0);
    }

    private TreeNode buildBinaryTree(Integer[] heapArray, int index) {
        if (index >= heapArray.length || heapArray[index] == null) {
            return null;
        }

        TreeNode root = new TreeNode(heapArray[index]);
        root.left = buildBinaryTree(heapArray, 2 * index + 1);
        root.right = buildBinaryTree(heapArray, 2 * index + 2);

        return root;
    }
}
