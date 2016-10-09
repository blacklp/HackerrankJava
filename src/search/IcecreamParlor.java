package search;

import java.util.Scanner;

public class IcecreamParlor {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int numTests = stringToInt(s.nextLine());
		
		for (int i = 0; i < numTests; i++) {
			int M = stringToInt(s.nextLine());
			s.nextLine(); // skip N, unused
			int[] input = stringToIntArray(s.nextLine());
			int[] flavorIndices = getFlavorIndicesForMoney(input, M);
			System.out.println(flavorIndices[0] + " " + flavorIndices[1]);
		}
		s.close();
	}

	private static int[] getFlavorIndicesForMoney(int[] input, int money) {
		int[] result = new int[2];
		for (int i = 0; i < input.length; i++) {
			if (input[i] >= money) {
				continue;
			}
			for (int j = i+1; j < input.length; j++) {
				if (foundMatch(input[i], input[j], money)) {
					result[0] = getIndexStartingBy1(i);
					result[1] = getIndexStartingBy1(j);
					break;
				}
			}
		}
		return result;
	}

	private static int getIndexStartingBy1(int index) {
		return index + 1;
	}

	private static boolean foundMatch(int flavorPrice1, int flavorPrice2, int money) {
		int priceSum = flavorPrice1 + flavorPrice2;
		return priceSum == money;
	}

	private static int[] stringToIntArray(String s) {
		String[] arr = s.split(" ");
		int[] result = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String elem = arr[i];
			result[i] = stringToInt(elem);
		}
		return result;
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
