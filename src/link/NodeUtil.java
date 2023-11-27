package link;

public class NodeUtil {
    public static void main(String[] args) {
        Node node = generateIntegerNode(3, 10);
        System.out.print("before: ");
        printLink(node);
        Node newHead = reverseLink(node);
        System.out.println();
        System.out.print("after: ");
        printLink(newHead);
    }

    /**
     * 生成一个整数链表
     *
     * @param size 链表大小
     * @param range 元素范围（-range,range）
     */
    public static Node generateIntegerNode(int size, Integer range) {
        Node head = null;
        Node currentHead = null;
        for (int i = 0; i < size; i++) {
            Node node = new Node();
            node.value = (int)(Math.random() * range) - (int)(Math.random() * range);
            if (i == 0) {
                currentHead = node;
                head = node;
            } else {
               // 此处从第二个节点开始
                currentHead.next = node;
                currentHead = currentHead.next;
            }
        }
        return head;
    }
    /**
     * 反转链表
     * 注意：
     */
    public static Node reverseLink(Node head) {
        // 前一个节点
        Node before = null;
        Node oldBefore = null;
        // 当前节点
        Node currentHead = head;
        // 新的头节点
        Node newHead = null;
        while (currentHead != null) {
            if (currentHead.next == null) {
                newHead = currentHead;
                newHead.next = before;
                break;
            }
            // 头结点特殊处理，因为头结点的next要指向null
            if (currentHead == head) {
                before = currentHead;
                currentHead = currentHead.next;
                before.next = null;
            } else {
                oldBefore = before;
                before = currentHead;
                currentHead = currentHead.next;
                before.next = oldBefore;
            }
        }
        return newHead;
    }

    // 打印链表
    public static void printLink(Node head) {
      while (head != null) {
          System.out.print(head.value + " ");
          head = head.next;
      }
    }
}
