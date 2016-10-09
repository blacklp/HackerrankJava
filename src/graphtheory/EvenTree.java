package graphtheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EvenTree {
    private static List<int[]> edges = new ArrayList<>();

    public static void main() {
        Scanner s = new Scanner(System.in);
        int[] nAndM = stringToIntArray(s.nextLine());
        int m = nAndM[1];
        for (int i = 0; i < m; i++) {
            int[] edge = stringToIntArray(s.nextLine());
            edges.add(edge);
        }
        int removedEdges = getRemovedEdges();
        System.out.println(removedEdges);
        s.close();
    }

    private static int getRemovedEdges() {
        int bestEdgeListSize = 0;
        for (int i = 1; i < edges.size(); i++) {
            List<List<int[]>> edgesToBeKept = getEdgedToBeKept(i);
            if (foundGoodEdgeList(edgesToBeKept)) {
                bestEdgeListSize = i; // each iteration better since set grows (TODO: try from the end instead).
            }
        }
        return 0;
    }

    private static boolean foundGoodEdgeList(List<List<int[]>> edgesToBeUsed) {
        // TODO Auto-generated method stub
        // check if there is a list containing a list of edges for which all connected cmponents have size % 2 == 0
        return false;
    }

    private static List<List<int[]>> getEdgedToBeKept(int numToBeRemoved) {
        List<List<int[]>> result = new ArrayList<>();
        List<int[]> list = edges.subList(0, numToBeRemoved);
        result.add(list);

        for (int i = 0; i < numToBeRemoved; i++) {
            List<int[]> newList = new ArrayList<>(list);
            int[] currentEdge = newList.get(i);
            for (int j = 0; j < numToBeRemoved; j++) {
                if (j == i) {
                    newList.set(i, newList.get(j));
                    newList.set(j, currentEdge);
                    result.add(newList);
                    continue;
                }
            }
        }
        return result;
    }

    private static int[] stringToIntArray(String s) {
        String[] arr = s.split(" ");
        return Arrays.stream(arr).mapToInt(elem -> stringToInt(elem)).toArray();
    }

    private static int stringToInt(String elem) {
        return Integer.parseInt(elem);
    }
}
