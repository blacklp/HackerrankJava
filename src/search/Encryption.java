package search;

import java.util.Scanner;

public class Encryption {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		int[] rowsAndCols = calculateRowsAndColumns(line);
		char[][] grid = buildGrid(rowsAndCols[0], rowsAndCols[1], line);
		String readPerColumns = readPerColumns(grid, rowsAndCols[0], rowsAndCols[1]);
		System.out.print(readPerColumns);
		s.close();
	}

	private static String readPerColumns(char[][] grid, int rows, int cols) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				char c = grid[j][i];
				if (c != '\0')
					sb.append(c);
			}
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	private static char[][] buildGrid(int rows, int cols, String line) {
		char[][] result = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int index = i*cols + j;
				if (index >= line.length())
				{
					break;
				}
				result[i][j] = line.charAt(index);
			}
		}
		return result;
	}

	private static int[] calculateRowsAndColumns(String line) {
		int[] result = new int[2];
		int length = line.length();
		double sqrtLength = Math.sqrt(length);
		int min = (int)Math.floor(sqrtLength);
		int max = (int)Math.ceil(sqrtLength);
		
		for (int i = min; i <= max; i++) {
			for (int j = i; j <= max; j++) {
				if ((i * j) >= length) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		return result;
	}
}
