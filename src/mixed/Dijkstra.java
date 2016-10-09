package mixed;

import java.util.*;

/**
 * Created by luciapasarin on 28/12/15.
 */
public class Dijkstra {
    private static Map<Integer, List<Edge>> vertexToEdges;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        int numTestCases = parseInt(scanner.nextLine());

        for (int i = 0; i < numTestCases; i++) {
            String[] nAndM = scanner.nextLine().split(" ");
            int numNodes = parseInt(nAndM[0]);
            int numEdges = parseInt(nAndM[1]);
            initAdjacenciesAndLengths(numEdges, scanner);
            int S = parseInt(scanner.nextLine());
            int[] shortestDistance = calculateShortestDistance(S, numNodes);
            for (int j = 0; j < shortestDistance.length; j++) {
                if (j != 0 && j != S) {
                    System.out.print(shortestDistance[j] + " ");
                }
            }
            System.out.println();
        }
        scanner.close();
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start)/1000D + " sec.");
    }

    private static int[] calculateShortestDistance(int start, int numNodes) {
        int[] A = new int[numNodes + 1];
        Arrays.fill(A, -1); // initialize
        Set<Integer> X = new HashSet<>(numNodes);
        X.add(start);
        A[start] = 0; // distance(start,start) = 0
        while (X.size() != numNodes) {
            Edge edge = getEdgeWithSourceAndMinDistance(X, A);
            if (edge == null) {
                break; // it means there are nodes not reachable from 'start'.
            }
            Integer source = edge.start;
            Integer destination = edge.end;
            X.add(destination);
            A[destination] = A[source] + edge.distance;
        }
        return A;
    }

    private static Edge getEdgeWithSourceAndMinDistance(Set<Integer> sources, int[] distances) {
        int minDistance = Integer.MAX_VALUE;
        Edge minEdge = null;

        for (Integer source : sources) {
            List<Edge> edges = vertexToEdges.get(source);
            if (edges == null) {
                continue;
            }
            for (Edge edge : edges) {
                if (sources.contains(edge.end) == false) {
                    int oldDistance = distances[edge.start] == -1 ? 0 : distances[edge.start];
                    int currentDistance = oldDistance + edge.distance;
                    if (currentDistance < minDistance) {
                        minDistance = currentDistance;
                        minEdge = edge;
                    }
                }
            }
        }
        return minEdge;
    }

    private static void initAdjacenciesAndLengths(int numEdges, Scanner scanner) {
        vertexToEdges = new HashMap<>();

        for (int i = 0; i < numEdges; i++) {
            String[] xAndYAndR = scanner.nextLine().split(" ");
            int x = parseInt(xAndYAndR[0]);
            int y = parseInt(xAndYAndR[1]);
            int distance = parseInt(xAndYAndR[2]);

            List<Edge> adjacentForX = vertexToEdges.get(x);
            if (adjacentForX == null) {
                adjacentForX = new LinkedList<>();
                vertexToEdges.put(x, adjacentForX);
            }
            adjacentForX.add(new Edge(x, y, distance));
            List<Edge> adjacentForY = vertexToEdges.get(y);
            if (adjacentForY == null) {
                adjacentForY = new LinkedList<>();
                vertexToEdges.put(y, adjacentForY);
            }
            adjacentForY.add(new Edge(y, x, distance));
        }
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private static class Edge {
        int start, end, distance;

        Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return start + "--" + distance + "->" + end;
        }
    }
}
