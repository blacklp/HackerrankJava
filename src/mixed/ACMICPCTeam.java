package mixed;

import java.util.*;

/**
 * Created by luciapasarin on 29/12/15.
 */
public class ACMICPCTeam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nAndM = scanner.nextLine().split(" ");
        int n = parseInt(nAndM[0]);
        String[] input = new String[n];

        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextLine();
        }
        int[] result = getMaxTopicsAndNumTeams(input);
        System.out.println(result[0]);
        System.out.println(result[1]);
        scanner.close();
    }

    /**
     *
     * @param input
     * @return int[] {maxTopics, numTeamsWithMaxTopicTopics}
     */
    private static int[] getMaxTopicsAndNumTeams(String[] input) {
        TreeMap<Integer, List<Team>> teamToNumTopics = new TreeMap<>();

        for (int i = 0; i < input.length - 1; i++) { // (length - 1)th elem cannot be paired, was already paired.
            addGeneratedTeams(i, input, teamToNumTopics);
        }
        Map.Entry<Integer, List<Team>> entry = teamToNumTopics.lastEntry();
        return new int[] { entry.getKey(), entry.getValue().size() };
    }

    private static void addGeneratedTeams(int first, String[] input, TreeMap<Integer, List<Team>> teamToNumTopics) {
        for (int second = first + 1; second < input.length; second++) {
            Team team = new Team(first, second);
            int numTopics = calculateNumberOfTopics(input[first], input[second]);
            List<Team> teams = teamToNumTopics.get(numTopics);
            if (teams == null) {
                teams = new LinkedList<>();
                teamToNumTopics.put(numTopics, teams);
            }
            teams.add(team);
        }

    }

    /**
     * @pre s1.length() == s2.length()
     * @param s1 {0,1} sequence
     * @param s2 {0,1} sequence
     * @return
     */
    private static int calculateNumberOfTopics(String s1, String s2) {

        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            int digitFrom1 = s1.charAt(i) - '0';
            int digitFrom2 = s2.charAt(i) - '0';
            count += (digitFrom1 | digitFrom2);
        }
        return count;
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private static class Team {
        int first, second;

        public Team(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "(" + first + "," + second + ")";
        }
    }
}
