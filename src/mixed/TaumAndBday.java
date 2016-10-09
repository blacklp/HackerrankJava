package mixed;

import java.math.BigInteger;
import java.util.Scanner;

public class TaumAndBday {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numTestCases = parseInt(scanner.nextLine());

        for (int i = 0; i < numTestCases; i++) {
            String[] bAndW = scanner.nextLine().split(" ");
            String[] xYAndZ = scanner.nextLine().split(" ");
            BigInteger b = parseBigInt(bAndW[0]);
            BigInteger w = parseBigInt(bAndW[1]);
            BigInteger x = parseBigInt(xYAndZ[0]);
            BigInteger y = parseBigInt(xYAndZ[1]);
            BigInteger z = parseBigInt(xYAndZ[2]);
            BigInteger amount = calculateMinAmountSpent(b, w, x, y, z);
            System.out.println(amount);
        }
        scanner.close();
    }

    private static BigInteger calculateMinAmountSpent(BigInteger numBlack, BigInteger numWhite, BigInteger priceBlack, BigInteger priceWhite, BigInteger priceTransform) {

        BigInteger noTransform = numBlack.multiply(priceBlack).add(numWhite.multiply(priceWhite));
        BigInteger totalNumGifts = numBlack.add(numWhite);
        BigInteger minPrice = priceBlack.min(priceWhite);
        BigInteger numToBeTransformed = minPrice.equals(priceWhite) ? numBlack : numWhite;

        BigInteger transformed = totalNumGifts.multiply(minPrice).add(numToBeTransformed.multiply(priceTransform));
        return transformed.min(noTransform);
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private static BigInteger parseBigInt(String s) {
        return BigInteger.valueOf(Integer.parseInt(s));
    }
}
