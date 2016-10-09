package graphtheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BreadthFirstSearchShortestReach {
    private static Map<Integer, List<Integer>> adjacencies = new HashMap<>();
    private static int[] distances;
    private static Set<Integer> fringe = new HashSet<>();

    private static final int weight = 6;
    private static final int Infinity = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = strToInt(s.nextLine());
        for (int i = 0; i < T; i++) {
            int[] nAndM = strToIntArray(s.nextLine());
            int n = nAndM[0];
            distances = new int[n + 1]; // nodes start with 1
            int m = nAndM[1];
            for (int j = 0; j < m; j++) {
                int[] xAndY = strToIntArray(s.nextLine());
                int x = xAndY[0];
                int y = xAndY[1];
                addAdjacency(x, y);
                addAdjacency(y, x);
            }
            int S = strToInt(s.nextLine());
            calculateShortestDistances(S);

            for (int ind = 1; ind < distances.length; ind++) { // starts with 1
                if (ind == S) {
                    continue;
                }
                int dist = distances[ind];
                System.out.print((dist == Infinity ? -1 : dist) + " ");
            }
        }
        s.close();
    }

    private static void calculateShortestDistances(int start) {
        Arrays.fill(distances, Infinity);
        for (int neighbor : adjacencies.get(start)) {
            distances[neighbor] = weight;
            fringe.add(neighbor);
        }

        while (fringe.isEmpty() == false) {
            int v = removeFromFringeWithMinDistance();
            for (int neighbor : adjacencies.get(v)) {
                if (neighbor == start) {
                    continue;
                }
                int oldDistance = distances[neighbor];
                int newDistance = distances[v] + weight;
                if (oldDistance == Infinity) {
                    distances[neighbor] = newDistance;
                    fringe.add(neighbor);
                } else if (newDistance < oldDistance) {
                    distances[neighbor] = newDistance;
                }
            }
        }
    }

    private static int removeFromFringeWithMinDistance() {
        int result = 0;
        int minPossibleDistance = weight;
        int minDistance = Infinity;
        for (int v : fringe) {
            if (minDistance == minPossibleDistance) {
                break;
            }
            int currentDist = distances[v];
            if (currentDist < minDistance) {
                minDistance = currentDist;
                result = v;
            }
        }
        fringe.remove(result);
        return result;
    }

    private static void addAdjacency(Integer start, Integer end) {
        List<Integer> list = adjacencies.get(start);
        if (list == null) {
            list = new ArrayList<>();
            adjacencies.put(start, list);
        }
        list.add(end);
    }

    private static int[] strToIntArray(String s) {
        return Arrays.stream(s.split(" ")).mapToInt(elem -> strToInt(elem)).toArray();
    }

    private static int strToInt(String s) {
        return Integer.parseInt(s);
    }
}
