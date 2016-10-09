package mixed;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by luciapasarin on 16/08/15.
 */
public class BigIntegerEX {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        BigInteger num1 = stringToBigInt(s.nextLine());
        BigInteger num2 = stringToBigInt(s.nextLine());
        System.out.println(num1.add(num2));
        System.out.println(num1.multiply(num2));
        s.close();
    }

    private static BigInteger stringToBigInt(String s) {
        return new BigInteger(s);
    }
}
