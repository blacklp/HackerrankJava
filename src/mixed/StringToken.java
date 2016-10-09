package mixed;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luciapasarin on 16/08/15.
 */
public class StringToken {
    private static final String regex = "[A-Za-z]+";
    private static final Pattern pattern = Pattern.compile(regex);
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        final String input = s.nextLine();
        final Matcher matcher = pattern.matcher(input);
        int numFound = 0;
        List<String> tokens = new ArrayList<>();

        while (matcher.find()) {
            String token = matcher.group();
            tokens.add(token);
            numFound++;
        }
        System.out.println(numFound);
        tokens.forEach(t -> System.out.println(t));
    }
}
