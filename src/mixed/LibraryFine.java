package mixed;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Created by luciapasarin on 27/12/15.
 */
public class LibraryFine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate returnedDate = parseDate(scanner.nextLine());
        LocalDate expectedDate = parseDate(scanner.nextLine());
        int fine = calculateFine(returnedDate, expectedDate);
        System.out.println(fine);
        scanner.close();
    }

    private static int calculateFine(LocalDate returnedDate, LocalDate expectedDate) {
        boolean sameMonth = returnedDate.getMonth() == expectedDate.getMonth();
        boolean sameYear = returnedDate.getYear() == expectedDate.getYear();

        if (returnedDate.equals(expectedDate) || returnedDate.isBefore(expectedDate)) {
            return 0;
        } else if (sameMonth && sameYear) {
            int differenceInDays = returnedDate.getDayOfMonth() - expectedDate.getDayOfMonth();
            return 15 * differenceInDays;
        } else if (sameYear) {
            int differenceInMonths = returnedDate.getMonth().getValue() - expectedDate.getMonth().getValue();
            return 500 * differenceInMonths;
        }
        return 10000;
    }

    private static LocalDate parseDate(String s) {
        String[] array = s.split(" ");

        int day = parseInt(array[0]);
        int month = parseInt(array[1]);
        int year = parseInt(array[2]);

        LocalDate date = LocalDate.of(year, month, day);

        return date;
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
