package greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class JimAndTheOrders {
	private static final Map<Integer, List<Integer>> serviceTimeToFans = new TreeMap<>();
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int numBurgerFans = stringToInt(s.nextLine());
		for (int i = 0; i < numBurgerFans; i++) {
			int[] dAndT = stringToIntArray(s.nextLine());
			addServiceTime(dAndT[0], dAndT[1], i+1);
		}
		printResult();
		s.close();
	}
	
	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		for (List<Integer> fans : serviceTimeToFans.values()) {
			for (Integer fan : fans) {
				sb.append(fan).append(" ");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

	private static void addServiceTime(int orderTime, int waitingTime, int fan) {
		Integer serviceTime = orderTime + waitingTime;
		List<Integer> fans = serviceTimeToFans.get(serviceTime);
		if (fans == null) {
			fans = new ArrayList<>();
			serviceTimeToFans.put(serviceTime, fans);
		}
		fans.add(fan);
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
