package mixed;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by luciapasarin on 16/08/15.
 */
public class OnedArray {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numTests = stringToInt(s.nextLine());
        for (int i = 0; i < numTests; i++) {
            int[] nAndM = stringToIntArray(s.nextLine());
            int[] array = stringToIntArray(s.nextLine());
            boolean hasSolution = hasSolution(array, nAndM[1]);
            System.out.println(hasSolution ? "YES" : "NO");
        }
        s.close();
    }

    private static int stringToInt(String s) {
        return Integer.parseInt(s);
    }

    private static int[] stringToIntArray(String s) {
        return Arrays.stream(s.split(" ")).mapToInt(elem -> stringToInt(elem)).toArray();
    }

    private static boolean hasSolution(int[] game, int m) {
        int[] visited = new int[game.length];
        Arrays.fill(visited, 0);
        return check(game, 0, m, visited);
    }

    private static boolean check(int[] game, int pos, int m, int[] visited) {
        if (pos >= game.length) {
            return true;
        }
        if (game[pos] == 1) {
            return false;
        }
        visited[pos] = 1; // visited
        return check(game, pos + 1, m, visited) || check(game, pos + m, m, visited) ||
                (pos > 0 && visited[pos - 1] == 0 ? check(game, pos - 1, m, visited) : false);
    }
}
