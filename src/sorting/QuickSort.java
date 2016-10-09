package sorting;

import java.util.Scanner;

public class QuickSort {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		s.nextLine(); // ignore num elements in array (unused).
		int[] array = stringToIntArray(s.nextLine());
		quicksort(array);
		printArray(array);
		s.close();
	}
	
	private static void printArray(int[] array) {
		StringBuilder sb = new StringBuilder();
		for (int n : array) {
			sb.append(n).append(" ");			
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

	private static void quicksort(int[] input) {
		quicksort(input, 0, input.length - 1);
	}

	private static void quicksort(int[] input, int first, int last) {
		if (first < last) {
			int m = choosePivot(input, first, last);
			doSwaps(input, first, last);
			quicksort(input, first, m);
			quicksort(input, m+1, last);
		}
	}
	
	private static void doSwaps(int[] input, int first, int last) {
		int pivotIndex = choosePivot(input, first, last);
		int pivotValue = input[pivotIndex];
		
		for (int i = first; i <= last; i++) {
			if ((i > pivotIndex && input[i] < pivotValue) || (i < pivotIndex && input[i] > pivotValue)) {
				swap(input, i, pivotIndex);
			}
		}
	}

	private static void swap(int[] input, int index1, int index2) {
		int aux = input[index1];
		input[index1] = input[index2];
		input[index2] = aux;
	}
	
	private static int choosePivot(int[] input, int first, int last) {
		return (last - first) / 2 + first;
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
