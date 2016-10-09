package mixed;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by luciapasarin on 27/12/15.
 */
public class ManasaAndStones {
    private static int a, b, numStones;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = parseInt(scanner.nextLine());

        for (int i = 0; i < numTestCases; i++) {
            numStones = parseInt(scanner.nextLine());
            a = parseInt(scanner.nextLine());
            b = parseInt(scanner.nextLine());
            Set<Integer> possibleLastStones = generateStones();
            new TreeSet<>(possibleLastStones).forEach(stone -> System.out.print(stone + " "));
            System.out.println();
        }
        scanner.close();
    }

    private static Set<Integer> generateStones() {
        Set<Integer> previousStones = new HashSet<>();
        previousStones.add(0);

        for (int level = 1; level < numStones; level++) {
            Set<Integer> newPreviousStones = new HashSet<>();
            for (Integer previousStone : previousStones) {
                int newStoneUsingA = previousStone + a;
                int newStoneUsingB = previousStone + b;
                newPreviousStones.add(newStoneUsingA);
                newPreviousStones.add(newStoneUsingB);
            }
            previousStones = newPreviousStones;
        }
        return previousStones;
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
