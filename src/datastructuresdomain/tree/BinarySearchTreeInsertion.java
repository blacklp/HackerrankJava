package datastructuresdomain.tree;

class Node {
	int data;
	Node left;
	Node right;
}

public class BinarySearchTreeInsertion {
	static Node Insert(Node root, int value) {
		if (root == null) {
			root = newNode(value);
			System.out.println("returning new node with data: " + root.data + "(i.e. value=" + value + ")");
			return root;
		}
		Node current = root;
		while (true) {
			if (value < current.data) {
				if (current.left == null) { // insert
					current.left = newNode(value);
					break;
				}
				current = current.left;
			} else {
				if (current.right == null) { // insert
					current.right = newNode(value);
					break;
				}
				current = current.right;
			}
		}
		return current;
	} // FIXME: fails in one case

	static Node newNode(int value) {
		Node result = new Node();
		result.data = value;
		return result;
	}
	
	static Node InsertRec(Node root, int value) {
		return InsertRec(root, root, value);
	}
	
	static Node InsertRec(Node root, Node current, int value) {
		if (current == null) { // insert
			current = newNode(value);
			return root == null ? current : root;
		} else if (value < current.data) { // left case
			if (current.left == null) { // insert
				current.left = newNode(value);
			} else {
				InsertRec(root, current.left, value);
			}
		} else { // right case
			if (current.right == null) { // insert
				current.right = newNode(value);
			} else {
				InsertRec(root, current.right, value);
			}
		}
		return root;
	}
}
