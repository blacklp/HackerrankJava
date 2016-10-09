package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitNumber {
    private static final String SEPARATOR_REGEX = "(\\-| )";
    private static final String COUNTRY_CODE_REGEX = "(\\d{1,3})";
    private static final String AREA_CODE_REGEX = "(\\d{1,3})";
    private static final String PHONE_NUMBER_REGEX = "(\\d{4,10})";
    private static Pattern pattern;

    public static void main(String[] args) {
        initPattern();
        Scanner s = new Scanner(System.in);
        int numTests = stringToInt(s.nextLine());
        for (int i = 0; i < numTests; i++) {
            printExtractedParts(s.nextLine());
        }
        s.close();
    }

    private static void initPattern() {
        StringBuilder sb = new StringBuilder();
        sb.append(COUNTRY_CODE_REGEX);
        sb.append(SEPARATOR_REGEX);
        sb.append(AREA_CODE_REGEX);
        sb.append(SEPARATOR_REGEX);
        sb.append(PHONE_NUMBER_REGEX);
        pattern = Pattern.compile(sb.toString());
    }

    private static int stringToInt(String s) {
        return Integer.parseInt(s);
    }

    private static void printExtractedParts(String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String matched = matcher.group();
            String[] matchedAsArr = matched.split(SEPARATOR_REGEX);
            String countryCode = matchedAsArr[0];
            String areaCode = matchedAsArr[1];
            String phoneNumber = matchedAsArr[2];
            System.out.println("CountryCode=" + countryCode + ",LocalAreaCode="
                    + areaCode + ",Number=" + phoneNumber);
        }
    }
}
