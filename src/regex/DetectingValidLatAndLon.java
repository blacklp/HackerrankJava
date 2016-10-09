package regex;

import java.util.Scanner;
import java.util.regex.Pattern;

public class DetectingValidLatAndLon {
    private static Pattern PATTERN;

    public static void main(String[] args) {
        initRegexPattern();
        Scanner s = new Scanner(System.in);
        int numLines = intToString(s.nextLine());
        for (int i = 0; i < numLines; i++) {
            boolean isValid = isValid(s.nextLine());
            System.out.println(isValid ? "Valid" : "Invalid");
        }
        s.close();
    }

    private static boolean isValid(String input) {
        return PATTERN.matcher(input).find();
    }

    private static void initRegexPattern() {
        StringBuilder sb = new StringBuilder();
        sb.append("\\(");
        sb.append(getAnyNumberInXRangeRegex());
        sb.append(", ");
        sb.append(getAnyNumberInYRangeRegex());
        sb.append("\\)");
        String regex = sb.toString();
        PATTERN = Pattern.compile(regex);
    }

    /**
     *
     * @return regex for -180 <= Y <= 180
     */
    private static String getAnyNumberInYRangeRegex() {
        String oneDigit = getAnyDigitRegex();
        String twoDigit = new StringBuilder("(").append(oneDigit).append(oneDigit).append(")").toString();
        String threeDigit = "((1[0-7]\\d))";
        String extremeCase = "(180)";

        return getNumberRegex(extremeCase, twoDigit, threeDigit);
    }

    /**
     *
     * @return regex for -90 <= X <= 90
     */
    private static String getAnyNumberInXRangeRegex() {

        String twoDigit = "([1-8]\\d)";
        String extremeCase = "(90)";
        return getNumberRegex(extremeCase, twoDigit);
    }

    private static String getNumberRegex(String extremeCase, String... numbersRegex) {
        String optionalSign = getOptionalSignRegex();
        String oneDigit = getAnyDigitRegex();

        String optionalDecimal = getOptionalDecimalRegex();

        String numWithoutSignRegex = getNumWithoutSignRegex(optionalDecimal,
                extremeCase, oneDigit, numbersRegex);

        StringBuilder sb = new StringBuilder();
        sb.append(optionalSign);
        sb.append("(");
        sb.append(numWithoutSignRegex);
        sb.append(")");

        return sb.toString();
    }

    private static String getNumWithoutSignRegex(String optionalDecimal,
            String extremeCase, String oneDigit, String... numbersRegex) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(getNumWithDecimalRegex(optionalDecimal, oneDigit, numbersRegex));
        sb.append("|");
        sb.append(getNumWithoutDecimalRegex(extremeCase));
        sb.append(")");
        return sb.toString();
    }

    private static String getNumWithoutDecimalRegex(String extremeCase) {
        StringBuilder sb = new StringBuilder();
        sb.append("((");
        sb.append(extremeCase);
        sb.append(")");
        sb.append(getOptionalEmptyDecimalRegex());
        sb.append(")");
        return sb.toString();
    }

    private static String getOptionalEmptyDecimalRegex() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("(.(0)+)?");
        sb.append(")");
        return sb.toString();
    }

    private static String getNumWithDecimalRegex(String optionalDecimal,
            String oneDigit, String... numbersRegex) {
        StringBuilder sb = new StringBuilder();
        sb.append("((");
        sb.append(oneDigit);
        for (String numRegex : numbersRegex) {
            sb.append("|");
            sb.append(numRegex);
        }
        sb.append(")");
        sb.append(optionalDecimal);
        sb.append(")");
        return sb.toString();
    }

    private static String getOptionalDecimalRegex() {
        return "(\\.(\\d)+)?";
    }

    private static String getOptionalSignRegex() {
        return "(\\+|\\-|)";
    }

    private static String getAnyDigitRegex() {
        return "(\\d)";
    }

    private static int intToString(String s) {
        return Integer.parseInt(s);
    }
}
