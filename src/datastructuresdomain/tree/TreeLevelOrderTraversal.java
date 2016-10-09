package datastructuresdomain.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TreeLevelOrderTraversal {
    class Node {
        int data;
        Node left;
        Node right;
    }

    Map<Integer, List<Integer>> heightToData = new TreeMap<>();

    void LevelOrder(Node root) {
        LevelOrder(root, 0);
        for (List<Integer> list : heightToData.values()) {
            for (Integer data : list) {
                System.out.print(data + " ");
            }
        }
    }

    void LevelOrder(Node root, int height) {
        List<Integer> list = heightToData.get(height);
        if (list == null) {
            list = new ArrayList<>();
            heightToData.put(height, list);
        }
        list.add(root.data);
        if (root.left != null) {
            LevelOrder(root.left, height + 1);
        }
        if (root.right != null) {
            LevelOrder(root.right, height + 1);
        }
    }
}
