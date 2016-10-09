package mixed;

/**
 * Created by luciapasarin on 03/01/16.
 */
public class InsertNodeAtEndOfLinkedList {
    public static void main(String[] args) {
        Node result1 = Insert(null, 1);
        print(result1);
        Node result2 = Insert(result1, 2);
        print(result2);
    }

    private static void print(Node n) {
        System.out.println("list:");
        while (n != null) {
            System.out.println(n.data);
            n = n.next;
        }
        System.out.println("end_list");
    }

    /*
  Insert Node at the beginning of a linked list
  head pointer input could be NULL as well for empty list
  Node is defined as*/
  static class Node {
     int data;
     Node next;
  }
  /*
*/
// This is a "method-only" submission.
// You only need to complete this method.

    private static Node Insert(Node head,int x) {
        if (head == null) {
            head = new Node();
            head.data = x;
        } else {
            int toBeAdded = x;
            Node current = head;
            Node previous = null;
            while (current != null) {
                int aux = current.data;
                current.data = toBeAdded;
                toBeAdded = aux;
                previous = current;
                current = current.next;
            }
            current = new Node();
            current.data = toBeAdded;
            previous.next = current;
        }
        return head;
    }

}
