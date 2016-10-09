package codegolfchallenge;

import java.math.BigDecimal;
import java.util.Scanner;

public class TheTrigonometricRatios {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = Integer.parseInt(s.nextLine());
		for (int i = 0; i < n; i++) {
			float x = Float.parseFloat(s.nextLine());
			System.out.println(calculateSine(x));
			System.out.println(calculateCosine(x));
		}
		s.close();
	}
	
	private static float calculateSine(float x) {
		return calculateUsingSeries(x, 1);
	}
	
	private static float calculateCosine(float x) {
		return calculateUsingSeries(x, 0);
	}
	
	private static float calculateUsingSeries(float x, int initialExponent) {
		final int numTerms = 5;
		float result = 0;
		int exponent = initialExponent;
		
		for (int i = 0; i < numTerms; i++) {
			float term = (float)Math.pow(x, exponent) / factorial(exponent);
			if (i % 2 != 0) { // it is a negative term
				term = -term;
			}
			result += term;
			exponent += 2;
		}
		return new BigDecimal(result).setScale(3, BigDecimal.ROUND_HALF_EVEN).floatValue();
	}
	
	private static float factorial(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}
		return n * factorial(n-1);
	}
}
