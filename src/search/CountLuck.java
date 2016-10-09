package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountLuck {
    private static boolean[][] visited;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numTests = stringToInt(s.nextLine());

        for (int i = 0; i < numTests; i++) {
            int[] nAndM = stringToIntArray(s.nextLine());
            int N = nAndM[0];
            int M = nAndM[1];
            visited = new boolean[N][M];
            char[][] grid = buildGrid(N, M, s);
            int K = stringToInt(s.nextLine());
            boolean isNumWavesK = isEqualsNumWandWaves(grid, K);
            System.out.println(isNumWavesK ? "Impressed" : "Oops!");
        }
        s.close();
    }

    private static boolean isEqualsNumWandWaves(char[][] grid, int k) {
        Position current = getStartPosition(grid);
        int waveCount = getNumWandWaves(grid, current);
        System.out.println("wave count: " + waveCount);
        return waveCount == k;
    }

    private static int getNumWandWaves(char[][] grid, Position current) {
        int count = 0;
        markAsVisited(current);
        while (isExit(grid, current) == false) {
            List<Position> possibleNexts = getPossibleNextPositions(grid, current);
            int numPossibilities = possibleNexts.size();
            if (numPossibilities == 0) {
                break;
            }
            if (numPossibilities > 1) {
                count++;
            }
            for (Position possibleNext : possibleNexts) {
                count += getNumWandWaves(grid, possibleNext);
            }
        }
        return count;
    }

    private static void markAsVisited(Position p) {
        visited[p.X][p.Y] = true;
    }

    private static List<Position> getPossibleNextPositions(char[][] grid, Position current) {
        List<Position> result = new ArrayList<>();
        List<Position> generalOtions = getAllPossiblePositionsAround(current, grid);

        for (Position option : generalOtions) {
            if (isValidOption(option, grid)) {
                result.add(option);
            }
        }
        return result;
    }

    private static boolean isValidOption(Position p, char[][] grid) {
        return containsInvalidChar(grid, p) == false && isVisited(p) == false;
    }

    private static boolean isVisited(Position p) {
        return visited[p.X][p.Y];
    }

    private static boolean containsInvalidChar(char[][] grid, Position p) {
        final char invalidChar = 'X';
        return grid[p.X][p.Y] == invalidChar;
    }

    private static List<Position> getAllPossiblePositionsAround(Position current, char[][] grid) {
        List<Position> result = new ArrayList<>();

        addPositionToList(current.X - 1, current.Y, grid, result); // left
        addPositionToList(current.X + 1, current.Y, grid, result); // right
        addPositionToList(current.X, current.Y - 1, grid, result); // up
        addPositionToList(current.X, current.Y + 1, grid, result); // down
        return result;
    }

    private static void addPositionToList(int X, int Y, char[][] context, List<Position> list) {
        int N = context.length;
        int M = context[0].length;
        if (X < N && Y < M && X >= 0 && Y >= 0) {
            list.add(new Position(X, Y));
        }
    }

    private static boolean isExit(char[][] grid, Position p) {
        Position exitPosition = getExitPosition(grid);
        return p.equals(exitPosition);
    }

    private static Position getExitPosition(char[][] grid) {
        return getPositionForChar(grid, '*');
    }

    private static Position getStartPosition(char[][] grid) {
        return getPositionForChar(grid, 'M');
    }

    private static Position getPositionForChar(char[][] grid, char c) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == c) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    private static char[][] buildGrid(int N, int M, Scanner s) {
        char[][] grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            grid[i] = stringToCharArray(s.nextLine());
        }
        return grid;
    }

    private static char[] stringToCharArray(String s) {
        return s.toCharArray();
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

    private static class Position {
        public int X;
        public int Y;

        public Position(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Position == false) {
                return false;
            }
            Position p = (Position)o;
            return p.X == this.X && p.Y == this.Y;
        }

        @Override
        public String toString() {
            return new StringBuilder("[").append(X).append(",").append(Y).append("]").toString();
        }
    }
}
