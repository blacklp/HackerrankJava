package mixed;

import java.util.Arrays;
import java.util.Scanner;

public class SherlockAndArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = parseInt(scanner.nextLine());
        for (int i = 0; i < numTestCases; i++) {
            int N = parseInt(scanner.nextLine());
            int[] A = parseIntArray(scanner.nextLine());
            boolean contains = containsElemThatDividesEqually(A);
            System.out.println(contains ? "YES" : "NO");
        }
        scanner.close();
    }

    private static boolean containsElemThatDividesEqually(int[] A) {
        if (A.length == 1) {
            return true;
        }
        int totalSum = Arrays.stream(A).sum();
        int leftSum = 0;
        int rightSum = totalSum - A[0];

        for (int i = 1; i < A.length; i++) {
            leftSum += A[i - 1];
            rightSum -= A[i];
            if (leftSum == rightSum) {
                return true;
            }
        }
        return false;
    }

    private static int[] parseIntArray(String s) {
        String[] strArray = s.split(" ");
        int[] result = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            result[i] = parseInt(strArray[i]);
        }
        return result;
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}