package tree;

import java.util.LinkedList;

public class TreeUtils<T> {

    /**
     * 判断一个二叉树是否是平衡树
     * 平衡树的定义： 最大高度和最小高度差<=1
     *
     * @param x
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
