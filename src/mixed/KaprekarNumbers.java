package mixed;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class KaprekarNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        printKaprekarNumbersInRange(p, q);
        scanner.close();
    }

    /**
     * prints the number of and all Kaprekar numbers in the range [p,q]
     **/
    private static void printKaprekarNumbersInRange(int p, int q) {
        List<Integer> numbers = new LinkedList<>();
        for (int i = p; i <= q; i++) {
            long square = (long)i * (long)i;
            int numDigits = extractNumDigits(i);
            LAndR intPair = extractLAndR(square, numDigits);
            int l = intPair.getLeft();
            int r = intPair.getRight();
            if (l + r == i) {
                numbers.add(i);
            }
        }
        printNumbers(numbers);
    }

    private static void printNumbers(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            System.out.println("INVALID RANGE");
        } else {
            numbers.forEach(n -> System.out.print(n + " "));
            System.out.println();
        }
    }

    private static int extractNumDigits(int n) {
        int count = 0;
        while (n > 0) {
            n /= 10;
            count++;
        }
        return count;
    }

    private static LAndR extractLAndR(long n, int numDigits) {
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        int count = 0;

        while (n > 0) {
            long digit = n % 10;
            String digitAsString = Long.toString(digit);
            if (count < numDigits) {
                right.append(digitAsString);
            } else  {
                left.append(digitAsString);
            }
            count++;
            n /= 10;
        }
        return new LAndR(parseInt(left.reverse()), parseInt(right.reverse()));
    }

    private static int parseInt(StringBuilder sb) {
        if (sb.length() == 0) {
            return 0;
        }
        return Integer.parseInt(sb.toString());
    }

    private static class LAndR {
        int left;
        int right;

        LAndR(int left, int right) {
            this.left = left;
            this.right = right;
        }

        int getLeft() {
            return left;
        }

        int getRight() {
            return right;
        }
    }
}