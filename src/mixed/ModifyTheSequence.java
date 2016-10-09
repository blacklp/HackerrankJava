package mixed;

import java.util.Scanner;

/**
 * Created by luciapasarin on 23/12/15.
 */
public class ModifyTheSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer.parseInt(scanner.nextLine()); // sequence length (unused)
        int[] sequence = parseIntSequence(scanner.nextLine());
        int result = getReplacementsToMakeSequenceIncreasing(sequence);
        System.out.println(result);
        scanner.close();
    }

    private static int getReplacementsToMakeSequenceIncreasing(int[] sequence) {
        final int length = sequence.length;
        int[] copy = sequence.clone();

        int minReplacements = Integer.MAX_VALUE;
        for (int pivot = 0; pivot < length; pivot++) {
            int previous = sequence[pivot];
            int replacements = 0;
            for (int i = pivot + 1; i < length; i++) {
                int current = sequence[i];
                if (current <= previous) { // then replace
                    sequence[i] = previous + 1;
                    replacements++;
                }
                previous = sequence[i];
            }
            int next = sequence[pivot];
            for (int i = pivot - 1; i >= 0; i--) {
                int current = sequence[i];
                if (current >= next) { // then replace
                    int newValue = next -1;
                    if (newValue > 0) {
                        sequence[i] = newValue;
                        replacements++;
                    } else {
                        replacements = Integer.MAX_VALUE;
                        break;
                    }
                }
                next = sequence[i];
            }
            sequence = copy.clone();
            minReplacements = Math.min(minReplacements, replacements);
            if (minReplacements == 0) {
                break;
            }
        }
        return minReplacements;
    }

    private static int[] parseIntSequence(String s) {
        String[] strArray = s.split(" ");
        int[] result = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            result[i] = Integer.parseInt(strArray[i]);
        }
        return result;
    }
}
