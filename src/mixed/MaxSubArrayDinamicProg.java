package mixed;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by luciapasarin on 23/12/15.
 */
public class MaxSubArrayDinamicProg {
    private static int[] cache;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = parseNextInt(scanner.nextLine());
        for (int i = 0; i < numCases; i++) {
            int arrayLength = parseNextInt(scanner.nextLine());
            int[] array = parseNextIntArray(scanner.nextLine());
            initCache(arrayLength);
            String result = getMaxSubarray(array);
            System.out.println(result);
        }
        scanner.close();
    }

    private static void initCache(int length) {
        cache = new int[length];
        Arrays.fill(cache, 0);
    }

    private static String getMaxSubarray(int[] array) {
        int bestContiguousValue = Integer.MIN_VALUE;
        int bestNonContiguousValue = array[0];

        cache[0] = array[0];

        for (int i = 1; i < array.length; i++) {
            bestNonContiguousValue = getBestNonContiguousValue(bestNonContiguousValue, array[i]);
            for (int j = 0; j <= i; j++) {
                int oldValue = cache[j];
                int currentValue = oldValue + array[i];
                if (currentValue > bestContiguousValue) {
                    bestContiguousValue = currentValue;
                }
                cache[j] = currentValue;
            }
        }
        return bestContiguousValue + " " + bestNonContiguousValue;
    }

    private static int getBestNonContiguousValue(int currentBest, int currentValue) {
        if (currentBest + currentValue > currentBest) {
            currentBest += currentValue;
        }
        if (currentValue > currentBest) {
            currentBest = currentValue;
        }
        return currentBest;
    }

    private static int[] parseNextIntArray(String s) {
        String[] strArray = s.split(" ");
        int[] result = new int[strArray.length];

        for (int i = 0; i < strArray.length; i++) {
            result[i] = parseNextInt(strArray[i]);
        }
        return result;
    }

    private static int parseNextInt(String s) {
        return Integer.parseInt(s);
    }

}
