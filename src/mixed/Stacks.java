package mixed;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Created by luciapasarin on 16/08/15.
 */
public class Stacks {
    private static Set<Character> openingParentheses, closingParentheses;

    public static void main(String[] args) {
        initParentheses();

        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            String input = s.nextLine();
            boolean balanced = isBalanced(input);
            System.out.println(balanced);
        }
        s.close();
    }

    private static void initParentheses() {
        openingParentheses = new HashSet<>();
        closingParentheses = new HashSet<>();

        openingParentheses.add('(');
        openingParentheses.add('{');
        openingParentheses.add('[');

        closingParentheses.add(')');
        closingParentheses.add('}');
        closingParentheses.add(']');
    }

    private static boolean isBalanced(String s) {
        Stack<Character> expected = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isOpeningParenthesis(c)) {
                expected.push(getClosing(c));
            } else if (isClosingParenthesis(c)) {
                if (isExpected(expected, c)) {
                    expected.pop();
                } else {
                    return false;
                }
            }
        }
        return expected.isEmpty();
    }

    private static boolean isOpeningParenthesis(char c) {
        return openingParentheses.contains(c);
    }

    private static boolean isClosingParenthesis(char c) {
        return closingParentheses.contains(c);
    }

    private static char getClosing(char c) {
        switch (c) {
            case '(':
                return ')';
            case '{':
                return '}';
            case '[':
                return ']';
            default:
                return '\0';
        }
    }

    private static boolean isExpected(Stack<Character> expected, char c) {
        if (expected.isEmpty()) {
            return false;
        }
        return expected.peek() == c;
    }
}
