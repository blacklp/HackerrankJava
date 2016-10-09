package datastructuresdomain.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SwapNodesAlgo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = strToInt(s.nextLine()); // number of nodes
        Node root = newNode(1);
        for (int i = 1; i <= n; i++) {
            Node ith = find(root, i);
            int[] aAndB = stringToIntArr(s.nextLine());
            int a = aAndB[0]; // left child of i
            int b = aAndB[1]; // right child of i
            if (a != -1) {
                ith.left = newNode(a);
            }
            if (b != -1) {
                ith.right = newNode(b);
            }
        }
        int numKs = strToInt(s.nextLine());
        for (int i = 0; i < numKs; i++) {
            int k = strToInt(s.nextLine());
            List<Integer> ks = getKs(k, height(root));
            for (int ki : ks) {
                swapAtHeight(root, ki);
            }
            printInorder(root);
            System.out.println();
        }
        s.close();
    }

    private static void printInorder(Node root) {
        if (root.left != null) {
            printInorder(root.left);
        }
        System.out.print(root.data + " ");
        if (root.right != null) {
            printInorder(root.right);
        }
    }

    private static void swapAtHeight(Node root, int ki) {
        if (root == null) {
            return;
        }
        if (height(root) == ki) {
            swap(root);
        } else {
            if (root.left != null) {
                swapAtHeight(root.left, ki);
            }
            if (root.right != null) {
                swapAtHeight(root.right, ki);
            }
        }
    }

    private static void swap(Node root) {
        Node aux = root.left;
        root.left = root.right;
        root.right = aux;
    }

    private static List<Integer> getKs(int k, int height) {
        List<Integer> result = new ArrayList<>();
        int elem = k;
        for (int i = 2; elem <= height; i++) {
            result.add(elem);
            elem = k * i;
        }
        return result;
    }

    private static int height(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    private static Node newNode(int value) {
        Node result = new Node();
        result.data = value;
        return result;
    }

    private static Node find(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.data == value) {
            return root;
        }
        Node leftResult = null, rightResult = null;
        if (root.left != null) {
            leftResult = find(root.left, value);
        }
        if (root.right != null) {
            rightResult = find(root.right, value);
        }
        return leftResult == null ? rightResult : leftResult;
    }

    private static int[] stringToIntArr(String s) {
        return Arrays.stream(s.split(" ")).mapToInt(elem -> strToInt(elem)).toArray();
    }

    private static int strToInt(String s) {
        return Integer.parseInt(s);
    }
}
