package mixed;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaximiseSum {
    private static int[] cache;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = parseInt(scanner.nextLine());
        for (int i = 0; i < numTestCases; i++) {
            String[] nAndM = scanner.nextLine().split(" ");
//            int n = parseInt(nAndM[0]);
            int m = parseInt(nAndM[1]);
            cache = parseIntArray(scanner.nextLine());
            int result = maxSubArrayModuloSum(m);
            System.out.println(result);
        }
        scanner.close();
    }

    private static int maxSubArrayModuloSum(int m) {
        int modulo = 0;

        for (int offset = 1; offset < cache.length; offset++) {
            for (int i = 0; i < cache.length - offset; i++) {
                int currentElem = cache[i];
                int currentModulo = currentElem % m;
                if (currentModulo > modulo) {
                    modulo = currentModulo;
                }
                cache[i] = currentElem + cache[i + offset];
            }
        }
//        for (int subArrayLength = 1; subArrayLength <= array.length; subArrayLength++) {
//            for (int i = 0; i <= array.length - subArrayLength; i++) {
//                int elem = array[i];
//                List<Integer> used = new ArrayList<>();
//                used.add(elem);
//                List<Integer> available = getRightList(array, i);
//                int possibleMax = maxSubArrayModuloSum(used, available, subArrayLength, m, 0);
//                if (possibleMax > max) {
//                    max = possibleMax;
//                }
//            }
//        }
        return modulo;
    }

    private static List<Integer> getRightList(int[] array, int currentIndex) {
        List<Integer> result = new ArrayList<>();

        for (int i = currentIndex + 1; i < array.length; i++) {
            result.add(array[i]);
        }
        return result;
    }

    private static int maxSubArrayModuloSum(List<Integer> used, List<Integer> available, int subArrayLength, int m, int currentMax) {
        if (used.size() == subArrayLength) {
            return Math.max(currentMax, moduloSum(used, m));
        }

        for (int i = 0; i < available.size(); i++) {
            Integer currentElem = available.get(i);
            List<Integer> currentUsed = new ArrayList<>(used);
            currentUsed.add(currentElem);
            List<Integer> currentAvailable = new ArrayList<>(available);
            currentAvailable.remove(currentElem);
            int result = maxSubArrayModuloSum(currentUsed, currentAvailable, subArrayLength, m, currentMax);
            if (result > currentMax) {
                currentMax = result;
            }
        }
        return currentMax;
    }

    private static int moduloSum(List<Integer> list, int modulo) {
        return list.stream().mapToInt(Integer::intValue).sum() % modulo;
    }

    private static int[] parseIntArray(String s) {
        String[] strArray = s.split(" ");
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