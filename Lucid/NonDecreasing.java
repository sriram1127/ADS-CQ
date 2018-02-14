import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

public class NonDecreasing {

	/**
	 * Take a rectangular grid of numbers and find the length of the longest
	 * sub-sequence.
	 * 
	 * @return the length as an integer.
	 */

	public static int longestSequence(int[][] grid) {
		// TODO: implement this function
		int length = 0;
		int max = grid.length * grid[0].length;
		System.out.println(max);
		Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		int row = grid.length - 1;
		int col = grid[0].length - 1;
		for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= col; j++) {
				visited.put(i + j * 10, false);
			}
		}
		outerloop: for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= col; j++) {
				length = Math.max(length, travel(grid, row, col, i, j, visited, 0, 0));
				if (length == max) {
					break outerloop;
				}
			}
		}
		return length;
	}

	private static int travel(int[][] arr, int row, int col, int i, int j, Map<Integer, Boolean> v, int length,
			int maxLength) {
		if (!v.get(i + j * 10)) {
			maxLength = Math.max(maxLength, length + 1);
			v.put(i + j * 10, true);

			if (i > 0 && condition(arr, i, j, i - 1, j))
				maxLength = Math.max(maxLength, travel(arr, row, col, i - 1, j, v, length + 1, maxLength));

			if (j > 0 && condition(arr, i, j, i, j - 1))
				maxLength = Math.max(maxLength, travel(arr, row, col, i, j - 1, v, length + 1, maxLength));

			if (i > 0 && j > 0 && condition(arr, i, j, i - 1, j - 1))
				maxLength = Math.max(maxLength, travel(arr, row, col, i - 1, j - 1, v, length + 1, maxLength));

			if (i < row && condition(arr, i, j, i + 1, j))
				maxLength = Math.max(maxLength, travel(arr, row, col, i + 1, j, v, length + 1, maxLength));

			if (j < col && condition(arr, i, j, i, j + 1))
				maxLength = Math.max(maxLength, travel(arr, row, col, i, j + 1, v, length + 1, maxLength));

			if (i < row && j < col && condition(arr, i, j, i + 1, j + 1))
				maxLength = Math.max(maxLength, travel(arr, row, col, i + 1, j + 1, v, length + 1, maxLength));

			if (i < row && j > 0 && condition(arr, i, j, i + 1, j - 1))
				maxLength = Math.max(maxLength, travel(arr, row, col, i + 1, j - 1, v, length + 1, maxLength));

			if (i > 0 && j < col && condition(arr, i, j, i - 1, j + 1))
				maxLength = Math.max(maxLength, travel(arr, row, col, i - 1, j + 1, v, length + 1, maxLength));
			v.put(i + j * 10, false);

		}
		return maxLength;
	}

	private static boolean condition(int[][] arr, int a, int b, int c, int d) {
		return Math.abs(arr[a][b] - arr[c][d]) > 3;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int numRows = 0;
		int numCols = 0;
		String[] firstLine = reader.readLine().split("\\s+");
		numRows = Integer.parseInt(firstLine[0]);
		numCols = Integer.parseInt(firstLine[1]);

		int[][] grid = new int[numRows][numCols];

		for (int row = 0; row < numRows; row++) {
			String[] inputRow = reader.readLine().split("\\s+");

			for (int col = 0; col < numCols; col++) {
				grid[row][col] = Integer.parseInt(inputRow[col]);
			}
		}
		int length = longestSequence(grid);
		System.out.println(length);
	}
}
