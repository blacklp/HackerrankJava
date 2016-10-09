package datastructuresdomain.tree;

public class TreeHuffmanDecoding {
	class Node {
		public int frequency; // the frequency of this tree
		public char data;
		public Node left, right;
	}
	
	void decode(String S, Node root) {
		StringBuilder decoded = new StringBuilder();
		
		for (int i = 0; i < S.length(); i++) {
			Node current = root;
			while (current != null) {
				char code = S.charAt(i);
				if (code == '0') { // left
					if (isLeaf(current.left)) {
						decoded.append(current.left.data);
						break;
					}
					current = current.left;
					i++;
				} else if (code == '1') { // right
					if (isLeaf(current.right)) {
						decoded.append(current.right.data);
						break;
					}
					current = current.right;
					i++;
				}
			}
		}
		System.out.println(decoded.toString());
	}
	
	private boolean isLeaf(Node node) {
		return node.left == null && node.right == null;
	}
}
