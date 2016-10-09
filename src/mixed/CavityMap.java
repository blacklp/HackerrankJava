package mixed;

import java.util.Scanner;

/**
 * Created by luciapasarin on 26/12/15.
 */
public class CavityMap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mapSize = parseInt(scanner.nextLine());
        int[][] map = read2DIntArray(scanner, mapSize);
        char[][] replacedMap = getMapWithReplacedCavities(map);
        for (char[] row : replacedMap) {
            for (char elem : row) {
                System.out.print(elem);
            }
            System.out.println();
        }
        scanner.close();
    }

    private static char[][] getMapWithReplacedCavities(int[][] map) {
        char[][] result = new char[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                result[i][j] = isCavity(map, i, j) ? 'X' : (char)(map[i][j] + '0');
            }
        }
        return result;
    }

    private static boolean isCavity(int[][] map, int i, int j) {
        if (i == 0 || i == map.length - 1 || j == 0 || j == map[0].length - 1) {
            return false; // ignoring borders, only considering [1, length-1] for both i and j
        }
        int elem = map[i][j];
        int leftElem = map[i][j-1];
        int rightElem = map[i][j+1];
        int upElem = map[i-1][j];
        int downElem = map[i+1][j];

        return leftElem < elem && rightElem < elem && upElem < elem && downElem < elem;
    }

    private static int[][] read2DIntArray(Scanner scanner, int mapSize) {
        int[][] result = new int[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            result[i] = parseIntArray(scanner.nextLine());
        }
        return result;
    }

    private static int[] parseIntArray(String s) {
        String[] stringArray = s.split("");
        int[] result = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            result[i] = parseInt(stringArray[i]);
        }
        return result;
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

}
