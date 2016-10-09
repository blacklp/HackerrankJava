package datastructuresdomain.tree;

public class PreorderTraversal {
    /*
     * you only have to complete the function given below. Node is defined as
     * follows
     */
//	class Node {
//	    int data;
//	    Node left;
//	    Node right;
//	}

    static void Preorder(Node root) {
        System.out.print(root.data + " ");
        if (root.left != null) {
            Preorder(root.left);
        }
        if (root.right != null) {
            Preorder(root.right);
        }
    }
}
