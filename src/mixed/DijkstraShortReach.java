package mixed;

import java.util.*;

/**
 * Created by luciapasarin on 26/12/15.
 */
public class DijkstraShortReach {
    private static Map<Integer, List<Pair<Integer, Integer>>> adjacencies;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = parseInt(scanner.nextLine());

        for (int i = 0; i < numTestCases; i++) {
            String[] nAndM = scanner.nextLine().split(" ");
            int numEdges = parseInt(nAndM[1]);
            initAdjacenciesAndLengths(numEdges, scanner);
            int start = parseInt(scanner.nextLine());
            Map<Integer, Integer> nodeToDistance = calculateShortestDistance(start);
            Collection<Integer> distances = nodeToDistance.values();
            distances.forEach(distance -> System.out.print(distance + " "));
        }
        scanner.close();
    }

    private static void initAdjacenciesAndLengths(int numEdges, Scanner scanner) {
        adjacencies = new HashMap<>();

        for (int i = 0; i < numEdges; i++) {
            String[] xAndYAndR = scanner.nextLine().split(" ");
            int x = parseInt(xAndYAndR[0]);
            int y = parseInt(xAndYAndR[1]);
            int distance = parseInt(xAndYAndR[2]);

            List<Pair<Integer, Integer>> adjacentForX = adjacencies.get(x);
            if (adjacentForX == null) {
                adjacentForX = new LinkedList<>();
                adjacencies.put(x, adjacentForX);
            }
            adjacentForX.add(new Pair<>(y, distance));
            List<Pair<Integer, Integer>> adjacentForY = adjacencies.get(y);
            if (adjacentForY == null) {
                adjacentForY = new LinkedList<>();
                adjacencies.put(y, adjacentForY);
            }
            adjacentForY.add(new Pair<>(x, distance));
        }
        System.out.println(adjacencies);
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private static Map<Integer, Integer> calculateShortestDistance(int from) {
        Set<Integer> nodes = adjacencies.keySet();
        Map<Integer, Integer> nodeToDistance = fillWithInfinity(nodes, from); // result
        Set<Integer> visited = new HashSet<>();
        visited.add(from);
        expand(from, nodeToDistance, visited, 0);
        return nodeToDistance;
    }

    private static Map<Integer, Integer> fillWithInfinity(Set<Integer> nodes, Integer except) {
        Map<Integer, Integer> result = new HashMap<>();

        for (Integer node : nodes) {
            if (node.equals(except) == false) {
                result.put(node, Integer.MAX_VALUE);
            }
        }
        return result;
    }

    private static void expand(int initial, Map<Integer, Integer> nodeToDistance, Set<Integer> visited, int pathDistance) {
        System.out.println(nodeToDistance);
        List<Pair<Integer, Integer>> currentAdjacent = adjacencies.get(initial);
        Set<Integer> nodes = adjacencies.keySet();

        for (Pair<Integer, Integer> pair : currentAdjacent) {
            Integer destination = pair.getFirst();
            if (visited.contains(destination)) {
                continue;
            }
            Integer edgeLength = pair.getSecond();
            int newPathDistance = pathDistance + edgeLength;
//            System.out.println(nodeToDistance.get(destination) + " > " + newPathDistance + " for node " + destination);
            if (nodeToDistance.get(destination) > newPathDistance) {
                nodeToDistance.put(destination, newPathDistance);
            }
            visited.add(destination);
//            System.out.println(visited.size() + "<" + nodes.size());
            if (visited.size() < nodes.size()) {
                expand(destination, nodeToDistance, visited, newPathDistance);
            }
        }
    }

    private static class Pair<T1, T2> {
        private T1 first;
        private T2 second;

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

        public T1 getFirst() {
            return first;
        }

        public T2 getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return "(" + first + "," + second + ")";
        }
    }
}
