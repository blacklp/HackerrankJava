package datastructuresdomain.tree;

import java.util.Arrays;
import java.util.Scanner;

public class BSTLowestCommonAncestor {
//	class Node {
//		int data;
//		Node left;
//		Node right;
//	}

	static Node lca(Node root, int v1, int v2) {
		boolean bothInLeft = contains(root.left, v1) && contains(root.left, v2);
		if (bothInLeft) { // both in left
			return lca(root.left, v1, v2);
		}
		boolean bothInRight = contains(root.right, v1)
				&& contains(root.right, v2);
		if (bothInRight == false) {
			return root;
		}
		// both in right
		return lca(root.right, v1, v2);
	}

	private static boolean contains(Node root, int value) {
		if (root == null) {
			return false;
		}
		if (root.data == value) {
			return true;
		}
		return contains(root.left, value) || contains(root.right, value);
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		strToInt(s.nextLine()); // tree size
		int[] input = strToIntArray(s.nextLine());
		int[] values = strToIntArray(s.nextLine());
		Node root = getTree(input);
		Node lca = lca(root, values[0], values[1]);
		System.out.println("result: root->" + lca.data);// + ",left->" + lca.left.data + ",right->" + lca.right.data);
		s.close();
	}

	private static Node getTree(int[] input) {
		Node root = null;
		for (int elem : input) {
			root = BinarySearchTreeInsertion.InsertRec(root, elem);
		}
		return root;
	}

	private static int[] strToIntArray(String s) {
		return Arrays.stream(s.split(" ")).mapToInt(elem -> strToInt(elem)).toArray();
	}

	private static int strToInt(String s) {
		return Integer.parseInt(s);
	}
}
