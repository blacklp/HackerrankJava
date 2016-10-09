package string;

import java.util.Scanner;

public class BiggerIsGreater {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numTestCases = stringToInt(s.nextLine());

        for (int i = 0; i < numTestCases; i++) {
            String word = s.nextLine();
            String nextBiggerString = getNextBiggerString(word);
            System.out.println(nextBiggerString);
        }
        s.close();
    }

    private static String getNextBiggerString(String s) {
        StringBuilder original = new StringBuilder(s);
        StringBuilder sb = new StringBuilder();
        boolean isBigger = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (isBigger) {
                break;
            }
             // XXX changing last one can make us lose one of the previous ones that make is as big a the original one...
        }
        sb.append(original);
        return sb.toString();
    }

    private static int stringToInt(String s) {
        return Integer.parseInt(s);
    }
}
