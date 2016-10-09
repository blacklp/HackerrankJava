package mixed;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class MissingNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = parseInt(scanner.nextLine());
        int[] A = parseIntArray(scanner.nextLine());
        int m = parseInt(scanner.nextLine());
        int[] B = parseIntArray(scanner.nextLine());
        TreeSet<Integer> missing = getMissingNumbers(A, B);
        missing.forEach(elem -> System.out.print(elem + " "));
        scanner.close();
    }

    /**
     * @param A input array with missing elements
     * @param B input array with all the elements
     * @return sorted distinct elements contained in B but not in A
     **/
    private static TreeSet<Integer> getMissingNumbers(int[] A, int[] B) {
        TreeSet<Integer> result = new TreeSet<>();

        HashMap<Integer, Integer> AElemsToTimes = asMap(A);
        for (int i = 0; i < B.length; i++) {
            int BElem = B[i];
            Integer repetitions = AElemsToTimes.get(BElem);
            if (repetitions == null || repetitions.intValue() == 0) {
                result.add(BElem);
            } else {
                AElemsToTimes.put(BElem, repetitions - 1);
            }
        }
        return result;
    }

    private static HashMap<Integer, Integer> asMap(int[] array) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int elem = array[i];
            Integer count = result.get(elem);
            result.put(elem, count == null ? 1 : count + 1);
        }
        return result;
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private static int[] parseIntArray(String s) {
        String[] strArray = s.split(" ");
        int[] result = new int[strArray.length];

        for (int i = 0; i < strArray.length; i++) {
            result[i] = parseInt(strArray[i]);
        }
        return result;
    }
}