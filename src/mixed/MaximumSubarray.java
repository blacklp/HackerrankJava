package mixed;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by luciapasarin on 23/12/15.
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numCases; i++) {
            Integer.parseInt(scanner.nextLine()); // array length, unused
            int[] array = parseIntArray(scanner.nextLine());

            String result = getMaximumLengths(array);
            System.out.println(result);
        }
    }

    private static int[] parseIntArray(String s) {
        String[] strArray = s.split(" ");
        return Arrays.stream(strArray).mapToInt(Integer::parseInt).toArray();
    }

    private static String getMaximumLengths(int[] array) {
        int bestContiguousGlobalSum = Integer.MIN_VALUE;
        int bestNonContiguousGlobalSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int bestCurrentSum = array[i];
            int totalCurrentSum = bestCurrentSum;
            int bestCurrentNonContiguousSum = bestCurrentSum;
            boolean bestSeriesFinished = false;
            for (int j = i+1; j < array.length; j++) {
                totalCurrentSum += array[j];
                if (bestCurrentSum + array[j] > bestCurrentSum) {
                    if (bestSeriesFinished == false) {
                        bestCurrentSum += array[j];
                    }
                    bestCurrentNonContiguousSum += array[j];
                } else {
                    bestSeriesFinished = true;
                }
                bestCurrentSum = Math.max(bestCurrentSum, totalCurrentSum);
            }
            bestContiguousGlobalSum = Math.max(bestContiguousGlobalSum, bestCurrentSum);
            bestNonContiguousGlobalSum = Math.max(bestNonContiguousGlobalSum, bestCurrentNonContiguousSum);
        }
        return bestContiguousGlobalSum + " " + bestNonContiguousGlobalSum;
    }
}
