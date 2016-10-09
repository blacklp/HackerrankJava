package datastructuresdomain.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeTopView {
	class Node {
		int data;
		Node left;
		Node right;
	}

	private Map<Integer, Integer> horDistToHeight = new HashMap<>();
	private Map<Integer, Integer> horDistToData = new TreeMap<>();
	
	void top_view(Node root) {
		top_view(root, 0, 0);
		for (Integer data : new ArrayList<>(horDistToData.values())) {
            System.out.print(data + " ");
        }
	}
	
	void top_view(Node root, int horizontalDistance, int height) {
		if (root.left != null) {
			top_view(root.left, horizontalDistance - 1, height + 1);
		}
		Integer existingHeight = horDistToHeight.get(horizontalDistance);
		if (existingHeight == null|| existingHeight > height) {
			horDistToHeight.put(horizontalDistance, height);
			horDistToData.put(horizontalDistance, root.data);
		}
		if (root.right != null) {
			top_view(root.right, horizontalDistance + 1, height + 1);
		}
	}
}
