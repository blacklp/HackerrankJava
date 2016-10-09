package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class MarkAndToys {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[] nAndK = stringToIntArray(s.nextLine());
		int[] input = stringToIntArray(s.nextLine());
		s.close();
		int numToys = getNumToysRecursive(input, nAndK[1]);
		System.out.println(numToys);
	}

	private static int getNumToysRecursive(int[] input, int money) {
		int[] sorted = sort(input);
		return doRecursion(sorted, money, 0);
	}

	private static int doRecursion(int[] arr, int money, int index) {
		int price = arr[index];
		if (price <= money && index < arr.length - 1) {
			return 1 + doRecursion(arr, money - price, index + 1);
		}
		return 0;
	}

	private static int[] sort(int[] arr) {
		return sort(arr, 0, arr.length - 1);
	}
	
	private static int[] sort(int[] arr, int start, int end) {
		int m = (end - start) / 2 + start;
		if ((end - start) > 0) {
			int[] left = sort(arr, start, m);
			int[] right = sort(arr, m + 1, end);
			return merge(left, right);
		}
		return new int[] { arr[start] };
	}

	private static int[] merge(int[] left, int[] right) {
		int length = left.length + right.length;
		int[] result = new int[length];

		int leftIndex = 0, rightIndex = 0;
		for (int i = 0; i < length; i++) {
			int elemFromLeft = leftIndex < left.length ? left[leftIndex] : Integer.MAX_VALUE;
			int elemFromRight = rightIndex < right.length ? right[rightIndex] : Integer.MAX_VALUE;

			int min = Math.min(elemFromLeft, elemFromRight);
			if (min == elemFromLeft) {
				leftIndex++;
			} else if (min == elemFromRight) {
				rightIndex++;
			}
			result[i] = min;
		}
		return result;
	}

	private static int[] stringToIntArray(String s) {
		String[] arr = s.split(" ");
		int[] result = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String elem = arr[i];
			result[i] = Integer.parseInt(elem);
		}
		return result;
	}
}
