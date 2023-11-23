package link;

import java.util.Objects;

public class NodeUtil {
    // 生成一个整数链表
    public Node generateIntegerNode(Integer size, Integer range) {
        return null;
    }
    // 打印链表
    public static void printNode(Node head) {
        if (Objects.isNull(head)) {
            System.out.println("null");
        }
        Node next = null;
        do {
            System.out.print(head.value + ", ");
            if (Objects.nonNull(next)) {
                System.out.print(next.value);
                if (Objects.nonNull(next.next)) {
                    System.out.print(", ");
                }
            }
        } while ((next = head.next) != null);
    }
    // 反转链表

}
