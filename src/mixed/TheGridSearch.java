package mixed;

import java.util.Scanner;

/**
 * Created by luciapasarin on 25/12/15.
 */
public class TheGridSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = parseInt(scanner.nextLine());

        for (int i = 0; i < numTestCases; i++) {
            String[] rAndC = scanner.nextLine().split(" ");
            int numRows = parseInt(rAndC[0]);
            int numColumns = parseInt(rAndC[1]);
            int[][] grid = readGrid(scanner, numRows, numColumns);
            String[] patternRAndC = scanner.nextLine().split(" ");
            int numRowsForPattern = parseInt(patternRAndC[0]);
            int numColumnsForPattern = parseInt(patternRAndC[1]);
            int[][] pattern = readGrid(scanner, numRowsForPattern, numColumnsForPattern);
            boolean found = findPatternInGrid(grid, pattern);
            System.out.println(found ? "YES" : "NO");
        }
        scanner.close();
    }

    private static boolean findPatternInGrid(int[][] grid, int[][] pattern) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == pattern[0][0]) {
                    if (matches(grid, i, j, pattern)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean matches(int[][] grid, int iStart, int jStart, int[][] pattern) {
        boolean canXBeOutOfRange = iStart + pattern.length > grid.length;
        boolean canYBeOutOfRange = jStart + pattern[0].length > grid[0].length;
        if (canXBeOutOfRange || canYBeOutOfRange) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                int gridElement = grid[iStart+i][jStart+j];
                int patternElement = pattern[i][j];
                if (gridElement != patternElement) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] readGrid(Scanner scanner, int numRows, int numColumns) {
        int[][] result = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            result[i] = parseIntArray(scanner.nextLine());
        }
        return result;
    }

    private static int[] parseIntArray(String s) {
        String[] strArray = s.split("");
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
