package stack;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 先进先出
 */
public class StackQueue<T> {
    public static void main(String[] args) {
        StackQueue<Integer> queue = new StackQueue<>();
        AtomicInteger num = new AtomicInteger();
        Thread pushThread = new Thread(() -> {
            for(int j = 0; j< 1000; j++){
                queue.push(num.getAndIncrement());
            }
        });
        Thread popThread = new Thread(() -> {
            while (true){
                Integer pop = queue.pop();
                System.out.println(pop);
            }
        });
        pushThread.run();
        popThread.run();
    }

    private Stack<T> pushStack = new Stack();
    private Stack<T> popStack = new Stack();
    private ReentrantLock lock = new ReentrantLock();
    public T pop(){
        if (popStack.isEmpty()) {
            transfer2PopStack();
        }
        return popStack.pop();
    }

    private void transfer2PopStack() {
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
    }

    public void push(T t) {
        synchronized (this) {
            pushStack.push(t);
        }
    }
}
