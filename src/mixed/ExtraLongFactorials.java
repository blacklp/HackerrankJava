package mixed;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by luciapasarin on 29/12/15.
 */
public class ExtraLongFactorials {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigInteger number = BigInteger.valueOf(scanner.nextInt());
        BigInteger factorial = factorial(number);
        System.out.println(factorial);
        scanner.close();
    }

    private static BigInteger factorial(BigInteger number) {
        if (number.equals(BigInteger.ZERO) || number.equals(BigInteger.ONE)) { // if number == 0 || number == 1
            return BigInteger.ONE;
        }
        return number.multiply(factorial(number.subtract(BigInteger.ONE))); // number * factorial(number - 1)
    }
}
