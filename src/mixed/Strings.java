package mixed;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by luciapasarin on 16/08/15.
 */
public class Strings {
    private static TreeSet<String> substrings = new TreeSet<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        int substringSize = stringToInt(s.nextLine());
        addSubstrings(input, substringSize);
        System.out.println(substrings.first());
        System.out.println(substrings.last());
        s.close();
    }

    private static int stringToInt(String s) {
        return Integer.parseInt(s);
    }

    private static void addSubstrings(String input, int substringSize) {
        for (int i = 0; i < (input.length() - substringSize + 1); i++) {
            String substring = input.substring(i, i + substringSize);
            substrings.add(substring);
        }
    }
}
