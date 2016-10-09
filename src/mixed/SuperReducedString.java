package mixed;

import java.util.Scanner;

/**
 * Created by luciapasarin on 22/07/16.
 */
public class SuperReducedString {
    private static final char EMPTY = '\0'; // Also Character.MIN_VALUE

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        char[] chars = input.toCharArray();
        char previous = chars[0];
        boolean foundAtLeastOne;

        do { // first iteration is always executed
            chars = cleanup(chars);
            foundAtLeastOne = false;
            for (int i = 1; i < chars.length; i++) {
                char current = chars[i];
                if (previous == current) {
                    chars[i - 1] = EMPTY;
                    chars[i] = EMPTY;
                    previous = EMPTY;
                    foundAtLeastOne = true;
                } else {
                    previous = current;
                }
            }
        } while (foundAtLeastOne);
        String result = String.valueOf(chars);
        System.out.println(result.isEmpty() ? "Empty String" : result);
        s.close();
    }

    private static char[] cleanup(char[] chars) {
        int size = 0;

        for (char c : chars) {
            if (c != EMPTY) {
                size++;
            }
        }
        char[] result = new char[size];
        int index = 0;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != EMPTY) {
                result[index] = c;
                index++;
            }
        }
        return result;
    }
}
