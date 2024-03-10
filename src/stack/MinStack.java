package stack;

import java.util.Stack;

/**
 * 1、实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 1)pop、push、getMin操作的时间复杂度都是O(1)。
 * 2)设计的栈类型可以使用现成的栈结构。
 */
public class MinStack<T extends Comparable<T>> {
    public static void main(String[] args) {
        MinStack<Integer> stack = new MinStack<>();
        stack.push(3);
        stack.push(5);
        System.out.println(stack.getMin());  // Output: 3
        stack.push(2);
        System.out.println(stack.getMin());  // Output: 2
        stack.pop();
        System.out.println(stack.getMin());
    }
    private Stack<T> dataStack = new Stack<>();
    private Stack<T> minStack = new Stack<>();
    private T currentMin = null;

    public T pop(){
        if (dataStack.empty()) {
            throw new UnsupportedOperationException();
        }
        minStack.pop();
        return dataStack.pop();
    }
    public void push(T element){
        if (currentMin == null) {
            currentMin = element;
        }
        if (element.compareTo(currentMin) < 0) {
            currentMin = element;
        }
        minStack.push(currentMin);
        dataStack.push(element);
    }
    public T getMin(){
        if (dataStack.empty()){
            throw new UnsupportedOperationException();
        }
        return minStack.peek();
    }
}
