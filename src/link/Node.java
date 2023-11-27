package link;

/**
 * @Author Seth
 * @Date 2021/9/10
 */
public class Node<T> {
    public T value;
    public Node<T> next;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
