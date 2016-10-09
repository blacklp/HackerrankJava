package mixed;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DynamicArray {
    private static List<List<Integer>> sequences;
    private static int lastans = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nAndQ = scanner.nextLine().split(" ");
        int n = Integer.parseInt(nAndQ[0]);
        initSequences(n);
        int q = Integer.parseInt(nAndQ[1]);

        for (int i = 0; i < q; i++) {
            String[] query = scanner.nextLine().split(" "); // "type x y"
            int type = Integer.parseInt(query[0]);
            int x = Integer.parseInt(query[1]);
            int y = Integer.parseInt(query[2]);
            executeQuery(type, x, y, n);
        }
        scanner.close();
    }

    private static void executeQuery(int queryType, int x, int y, int n) {
        int seqIndex = -1;
        List<Integer> sequence = null;
        switch (queryType) {
            case 1: // add elem to sequence
                seqIndex = (x ^ lastans) % n;
                sequence = sequences.get(seqIndex);
                sequence.add(y);
                break;
            case 2: // print element from sequence
                seqIndex = (x ^ lastans) % n;
                sequence = sequences.get(seqIndex);
                int size = sequence.size();
                int elemIndex = y % size;
                if (elemIndex < size) {
                    Integer elem = sequence.get(elemIndex);
                    System.out.println(elem);
                }
                lastans = size;
                break;
            default:
                break;
        }
    }

    private static void initSequences(int numSequences) {
        sequences = new ArrayList<>(numSequences);
        for (int i = 0; i < numSequences; i++) {
            sequences.add(i, new ArrayList<>());
        }
    }
}