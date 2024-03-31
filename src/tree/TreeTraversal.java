package tree;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * 前序： 头左右
 * 中序：左头右
 * 后序：左右头
 */
public class TreeTraversal {
    public static void main(String[] args) {


    }

    /**
     * 实现二叉树的按层遍历
     * 1、头节点接入队列
     * 2、弹出的过程中右左孩子加入左孩子，有右孩子加入右孩子
     * 3、重复2
     */
    public static <T> void traversalTreeByLevel(TreeNode node){
        if (node == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.print(poll.value + " ");
            if (Objects.nonNull(poll.left)) {
                queue.add(poll.left);
            }
            if (Objects.nonNull(poll.right)){
                queue.add(poll.right);
            }
        }
    }

    private static void traversalTreeByLevel(TreeNode node, LinkedList<TreeNode> queue) {
        if (node.left != null) {
            queue.add(node.left);
        }
        if (node.right != null) {
            queue.add(node.right);
        }
        traversalTreeByLevel(node.left,queue);
        traversalTreeByLevel(node.right,queue);

    }

    // 前序递归
    public static void pre(TreeNode node){
        if (Objects.isNull(node)) {
            return;
        }
        System.out.print(node + "  ");
        pre(node.getLeft());
        pre(node.getRight());
    }

    // 中序递归
    public static void in(TreeNode node){
        if (Objects.isNull(node)) {
            return;
        }

        in(node.getLeft());
        System.out.print(node + "  ");
        in(node.getRight());
    }

    // 后序递归
    public static void pos(TreeNode node){
        if (Objects.isNull(node)) {
            return;
        }
        pos(node.getLeft());
        pos(node.getRight());
        System.out.print(node + "  ");
    }

    /**
     * 完全二叉树版本
     * 非递归实现后序遍历
     * 核心思路：
     *  1、先把全部左孩子加入栈
     *  2、始弹栈，弹出一个左节点，就将其对应的右节点加入栈，如果这个右节点没有左右孩子就弹出自己；
     *  3、如果加入栈中的右孩子有子节点就重复步骤1、2
     *
     */
    public void pos1(TreeNode<Integer> node){

    }

    public static void in1(TreeNode<Integer> node){
        if (Objects.isNull(node)){
            return;
        }
        // 1、先把树的左子节点全都加到栈中，直到左孩子为null
        Stack<TreeNode> stack = new Stack<>();
        stack.add(node); // 加入全部子节点的过程中要先把根节点加入栈
        while (node.getLeft() != null) {
            stack.add(node.getLeft());
            node = node.getLeft();
        }
        // 2、从栈中弹出一个节点，如果节点有右孩子，加入栈
        while(!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.print(pop + " ");
            while (pop.getRight() != null){
                stack.add(pop.getRight());
            }
        }
    }

    /**
     * 左程云版本
     * @param head
     */
    public static void in2(TreeNode<Integer> head){
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.getLeft();
                } else {
                    head = stack.pop();
                    System.out.print(head + " ");
                    head = head.getRight();
                }
            }
        }
    }

}
