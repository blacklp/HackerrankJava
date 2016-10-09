package mixed;

/**
 * Created by luciapasarin on 30/12/15.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Pairs {
    private static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nAndK = scanner.nextLine().split(" ");
        int k = parseInt(nAndK[1]);
        int[] numbers = parseIntArray(scanner.nextLine());
//        Arrays.stream(numbers).filter(n -> n < 0).forEach(n -> System.out.print(n + " "));
        calculateNumPairsWithDifference(numbers, k);
        System.out.println(count);
        scanner.close();
    }

    /**
     * Sorts <code>numbers</code> and increases <code>count</code> everytime a pair with difference
     * <code>difference</code> is found.
     * @param numbers
     * @param difference
     * @return sorted numbers
     */
    private static int[] calculateNumPairsWithDifference(int[] numbers, int difference) {
        if (numbers.length == 1) {
            return numbers;
        }
        int m = numbers.length / 2;
        int[] left = Arrays.copyOfRange(numbers, 0, m);
        int[] right = Arrays.copyOfRange(numbers, m, numbers.length);
        int[] leftResult = calculateNumPairsWithDifference(left, difference);
        int[] rightResult = calculateNumPairsWithDifference(right, difference);
        int[] result = merge(leftResult, rightResult, difference);
//        System.out.println("merged " + Arrays.toString(leftResult) + " and " + Arrays.toString(rightResult) + ": " + Arrays.toString(result));
        return result;
    }

    private static int[] merge(int[] left, int[] right, int difference) {

        int[] result = new int[left.length + right.length];
        int k = 0; // index used in result
        int i = 0, j = 0;
        while (i < left.length && j < right.length) {
            int leftElem = left[i];
            int rightElem = right[j];
            if (leftElem <= rightElem) {
                result[k] = leftElem;
                i++;
            } else {
                result[k] = rightElem;
                j++;
            }
            System.out.println("abs diff = |" + leftElem + " - " + rightElem + "| = " + absDifference(leftElem, rightElem));
            if (absDifference(leftElem, rightElem) == difference) {
                count++;
            }
            k++;
        }
        int lastFromLeft = left[left.length - 1];
        int lastFromRight = right[right.length - 1];
        addElemsStillToBeAnalysed(left, result, i, k, lastFromRight, difference);
        addElemsStillToBeAnalysed(right, result, j, k, lastFromLeft, difference);
        return result;
    }

    private static void addElemsStillToBeAnalysed(int[] toBeAdded, int[] result, int i1, int i2, int lastFromOther, int difference) {
        boolean isFirstAnalysed = true;
        while (i1 < toBeAdded.length) {
            int elem = toBeAdded[i1];
            if (isFirstAnalysed == false) { // 968295 - 967608
                System.out.println("abs diff = |" + elem + " - " + lastFromOther + "| = " + absDifference(elem, lastFromOther));
            }
            if (absDifference(elem, lastFromOther) == difference && isFirstAnalysed == false) {
                count++;
            }
            isFirstAnalysed = false;
            result[i2] = elem;
            i1++;
            i2++;
        }
    }

    private static int absDifference(int leftElem, int rightElem) {
        return Math.abs(leftElem - rightElem);
    }

//    private static int getNumPairsWithDifference(int[] numbers, int difference) {
//        int count = 0;
//        for (int i = 0; i < numbers.length - 1; i++) { // last one non-inclusive, since it was already paired
//            int numPairsForI = getNumPairsForNumber(numbers, difference, i);
//            count += numPairsForI;
//        }
//        return count;
//    }
//
//    private static int getNumPairsForNumber(int[] numbers, int difference, int numberIndex) {
//        int number = numbers[numberIndex];
//        int count = 0;
//
//        for (int i = numberIndex + 1; i < numbers.length; i++) {
//            int other = numbers[i];
//            int currentDifference = Math.abs(number - other);
//            if (currentDifference == difference) {
//                count++;
//            }
//        }
//        return count;
//    }

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
