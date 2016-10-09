package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Flowers {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String[] nAndK = s.nextLine().split(" ");
		int k = stringToInt(nAndK[1]);
		int[] input = stringToIntArray(s.nextLine());
		s.close();
		int minAmount = getMinimumAmount(input, k);
		System.out.println(minAmount);
	}

	private static int getMinimumAmount(int[] input, int numFriends) {
		TreeSet<Integer> prices = new TreeSet<>(Comparator.reverseOrder());
		Arrays.stream(input).forEach(elem -> prices.add(elem));
		int totalAmount = 0;
		int priceFactor = 0;
		int friendCount = 0;
		
		for (Integer price : prices) {
			if (friendCount == 0) {
				priceFactor++;
			}
			totalAmount += (priceFactor * price);
			friendCount = (friendCount + 1) % numFriends;
		}
		return totalAmount;
	}

	private static int[] stringToIntArray(String s) {
		String[] arr = s.split(" ");
		return Arrays.stream(arr).mapToInt(elem -> stringToInt(elem)).toArray();
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
