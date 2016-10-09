package mixed;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luciapasarin on 23/12/15.
 */
public class JavaStringToken {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        final String regex = "[A-Za-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<String> words = new LinkedList<>();
        int count = 0;
        while (matcher.find()) {
            String word = matcher.group();
            words.add(word);
            count++;
        }
        System.out.println(count);
        words.forEach(System.out::println);
        scanner.close();
    }
}
