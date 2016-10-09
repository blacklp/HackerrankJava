package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class GridChallenge {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int numTests = stringToInt(s.nextLine());
		
		for (int i = 0; i < numTests; i++) {
			int numLines = stringToInt(s.nextLine());
			boolean canBeRearranged = canGridBeArranged(numLines, s);
			System.out.println(canBeRearranged ? "YES" : "NO");
		}
		s.close();
	}

	private static boolean canGridBeArranged(int N, Scanner s) {
		boolean result = true;
		char[][] grid = new char[N][N];
		for (int i = 0; i < N; i++) {
			char[] chars = s.nextLine().toCharArray();
			Arrays.sort(chars);
			grid[i] = chars;
			if (areColumnsSorted(grid, i) == false) {
				result = false; // we cannot quite, we need to do all necessary "s.nextLine()"s
			}
		}
		return result;
	}

	private static boolean areColumnsSorted(char[][] grid, int currentRowIndex) {
		if (currentRowIndex == 0) {
			return true;
		}
		char[] currentRow = grid[currentRowIndex];
		char[] previousRow = grid[currentRowIndex - 1];
		
		for (int i = 0; i < currentRow.length; i++) {
			char currentChar = currentRow[i];
			char previousChar = previousRow[i];
			if (currentChar < previousChar) {
				return false;
			}
		}
		return true;
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
