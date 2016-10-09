package mixed;

import java.util.*;

/**
 * Created by luciapasarin on 22/07/16.
 */
public class NonDivisibleSubset {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String[] nAndK = s.nextLine().split(" ");
        int n = Integer.parseInt(nAndK[0]);
        int k = Integer.parseInt(nAndK[1]);

        String[] array = s.nextLine().split(" ");
        Set<Integer> intSet= toIntSet(array);

        int result = getMaxNonDivisibleSubsetSize(intSet, k);
        System.out.println(result);

        s.close();
    }

    private static int getMaxNonDivisibleSubsetSize(Set<Integer> set, int k) {
        List<Integer> list = new ArrayList<>(set);
        int[][] cache = buildCache(list, k);

        int maxLength = -1;
        Set<Integer> currentResult; // contains indexes to be used by the cache
        int size = set.size();

        for (int i = 0; i < size - 1; i++) {
            currentResult = new HashSet<>();
            currentResult.add(i);

            for (int j = i + 1; j < size; j++) {
                if (isCompatibleWithAllInResult(j, currentResult, cache)) {
                    currentResult.add(j);
                }
            }
            maxLength = Math.max(maxLength, currentResult.size());
        }
        return maxLength;
    }

    private static int[][] buildCache(List<Integer> list, int k) {
        int n = list.size();
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            result[i] = new int[n];
        }

        for (int i = 0; i < n - 1; i++) {
            int[] row = result[i];
            int elem1 = list.get(i);
            for (int j = i + 1; j < n; j++) {
                int[] inverseRow = result[j];
                int elem2 = list.get(j);
                boolean isCompatible = isCompatible(elem1, elem2, k);
                int bit = isCompatible ? 1 : 0;
                row[j] = bit;
                inverseRow[i] = bit;
                result[j] = inverseRow;
            }
            result[i] = row;
        }
        return result;
    }

    private static boolean isCompatible(int n1, int n2, int k) {
        return (n1 + n2) % k != 0;
    }

    private static boolean isCompatibleWithAllInResult(int elemIndex, Set<Integer> indexResult, int[][] cache) {
        int[] cacheRow = cache[elemIndex];

        for (Integer index : indexResult) {
            if (cacheRow[index] == 0) { // incompatible
                return false;
            }
        }
        return true;
    }

    private static Set<Integer> toIntSet(String[] a) {
        Set<Integer> result = new HashSet<>();

        for (String s : a) {
            result.add(Integer.parseInt(s));
        }
        return result;
    }

}
