
/**
 * Created by luciapasarin on 02/01/16.
 */
public class SherlockAndTheBeast {
    public static void main(String[] args) {
        int numDigits = 100000;
        long maxValue = getMaxValue( numDigits);
        boolean isGreaterThanLong = maxValue < 0; // i.e. negative because of out of range
        System.out.println("greater_than_long = " + isGreaterThanLong);
    }

    private static long getMaxValue(int numDigits) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numDigits; i++) {
            sb.append("9");
        }
        return Long.valueOf(sb.toString());
    }
}
