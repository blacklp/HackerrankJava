package mixed;

/**
 * Created by luciapasarin on 03/01/16.
 */
public class InsertNodeAtSpecificPositionInLinkedList {
    /*
  Insert Node at a given position in a linked list
  head can be NULL
  First element in the linked list is at position 0
  Node is defined as */
  static class Node {
     int data;
     Node next;
  }

    public static void main(String[] args) {
        Node result = null;
        result = InsertNth(result, 3, 0);
        print(result);
        result = InsertNth(result, 5, 1);
        print(result);
        result = InsertNth(result, 4, 2);
        print(result);
        result = InsertNth(result, 2, 4);
        print(result);
        result = InsertNth(result, 10, 1);
        print(result);
    }

    private static void print(Node n) {
        while (n != null) {
            System.out.print(n.data);
            if (n.next != null) {
                System.out.print("->");
            }
            System.out.println();
            n = n.next;
        }
    }


    static Node InsertNth(Node head, int data, int position) {
        if (head == null) {
            head = new Node();
            head.data = data;
        } else {
            Node node = findNthNode(head, position);
            int saved = data;
            Node previous = node;
            while (node != null) {
                int aux = node.data;
                node.data = saved;
                saved = aux;
                previous = node;
                node = node.next;
            }
            node = new Node();
            node.data = saved;
            previous.next = node;
        }
        return head;
    }

    private static Node findNthNode(Node head, int position) {
        Node result = head;
        Node previous = null;

        for (int i = 0; i < position; i++) {
            if (result == null) {
                result = new Node();
                result.data = -1;
                if (previous != null) {
                    previous.next = result;
                }
            }
            previous = result;
            result = result.next;
        }
        return result;
    }

}
