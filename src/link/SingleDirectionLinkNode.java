package link;

/**
 * @Author Seth
 * @Date 2021/9/10
 */
public class SingleDirectionLinkNode<T> {

    public static void main(String[] args) {
        SingleDirectionLinkNode link = new SingleDirectionLinkNode<Integer>();
        link.offer(1);
        link.offer(2);
        link.offer(3);
        System.out.println("link.len() = " + link.len());
        link.print();

        System.out.println("======================");

        System.out.println("link.poll() = " + link.poll());
        System.out.println("link.len() = " + link.len());
        link.print();

        System.out.println("======================");

        System.out.println(link.peek());
        System.out.println("link.len() = " + link.len());
        link.print();

        System.out.println("======================");
        link.poll();
        link.poll();
        link.poll();
        System.out.println("link.length = " + link.length);
    }


    private void print(){
        if (head == null) {
            return;
        }
        Node cur = head;
        while ( cur!= null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
    }

    private int length = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    public int len(){
        return this.length;
    }

    /**
     * 增加节点
     */
    public void offer(T value) {
        if (head == null) {
            head = new Node<>();
            head.value = value;
            tail = head;
            head.next = null;
        } else {
            tail.next = new Node();
            tail.next.value = value;
            tail = tail.next;
        }
        length++;
    }

    /**
     * 弹出节点
     */
    public T poll(){
        if (head != null) {
            T ans = head.value;
            head = head.next;
            length--;
            return ans;
        } else {
            return null;
        }
    }

    /**
     * 弹出值，不删除节点
     * @return
     */
    public T peek(){
        if (head == null) {
            return null;
        } else {
            return head.value;
        }
    }

}
