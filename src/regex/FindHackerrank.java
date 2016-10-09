package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindHackerrank {
	private static Pattern startPattern;
	private static Pattern endPattern;
	private static Pattern startAndEndPattern;
	
	public static void main(String[] args) {
		initRegexPatterns();
		Scanner s = new Scanner(System.in);
		int numLines = stringToInt(s.nextLine());
		for (int i = 0; i < numLines; i++) {
			int match = getMatch(s.nextLine());
			System.out.println(match);
		}
		s.close();
	}

	private static void initRegexPatterns() {
		final String hackerrank = "hackerrank";
		final String anyStr = ".+";
		final String startChar = "^";
		final String endChar = "$";
		final String startRegex = startChar + hackerrank + anyStr + endChar;
		final String endRegex = startChar + anyStr + hackerrank + endChar;
		final String startAndEndRegex = startChar + hackerrank + endChar;
		
		startPattern = Pattern.compile(startRegex);
		endPattern = Pattern.compile(endRegex);
		startAndEndPattern = Pattern.compile(startAndEndRegex);		
	}

	private static int getMatch(String input) {
		if (startsWithHackerrank(input)) {
			return 1;
		}
		if (endsWithHackerrank(input)) {
			return 2;
		}
		if (startsAndEndsWithHackerrank(input)) {
			return 0;
		}
		return -1;
	}

	private static boolean startsWithHackerrank(String input) {
		return matches(startPattern, input);
	}
	
	private static boolean endsWithHackerrank(String input) {
		return matches(endPattern, input);
	}

	private static boolean startsAndEndsWithHackerrank(String input) {
		return matches(startAndEndPattern, input);
	}
	
	private static boolean matches(Pattern pattern, String input) {
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}
	
	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
