package mixed;

import java.util.Arrays;
import java.util.Scanner;

public class NewYearChaos {
    private static final int maxAllowedBribes = 2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            int n = in.nextInt();
            int q[] = new int[n];
            for(int q_i=0; q_i < n; q_i++){
                q[q_i] = in.nextInt();
            }
            int result = getNumMoves(n, q);
            System.out.println(result == -1 ? "Too chaotic" : result);
        }
    }

    private static int getNumMoves(int n, int[] q) {
        // TODO: count per element the number of moves used/available
        int result = 0;
        int i = 0;
        while (i < n) {
            int elem = q[i];
            int correctPosition = elem - 1; // 0-indexed array
            boolean isBetterPositioned = i < correctPosition; // current elem bribed
            int numBribes = (correctPosition - i);
            if (isBetterPositioned) {
                if (numBribes > maxAllowedBribes) {
                    return -1; // invalid
                } else {
                    revert(q, i, correctPosition);
                    result += numBribes;
                    i = 0;
                }
            } else {
                i++;
            }
        }
        return result;
    }

    /**
     * pre: current < correct
     */
    private static void revert(int[] array, int current, int correct) {
        for (int i = current; i < correct; i++) {
            swap(array, i, i+1);
        }
        System.out.println(Arrays.toString(array));
    }

    private static void swap(int[] array, int i, int j) {
        int aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }
}
