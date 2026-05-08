import java.util.*;

class Solution {

    int count = 0;

    public int totalNQueens(int n) {

        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>(); // row - col
        Set<Integer> diag2 = new HashSet<>(); // row + col

        backtrack(0, n, cols, diag1, diag2);

        return count;
    }

    private void backtrack(int row, int n,
                           Set<Integer> cols,
                           Set<Integer> diag1,
                           Set<Integer> diag2) {

        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {

            if (cols.contains(col) ||
                diag1.contains(row - col) ||
                diag2.contains(row + col)) {

                continue;
            }

            // Place queen
            cols.add(col);
            diag1.add(row - col);
            diag2.add(row + col);

            backtrack(row + 1, n, cols, diag1, diag2);

            // Remove queen
            cols.remove(col);
            diag1.remove(row - col);
            diag2.remove(row + col);
        }
    }
}
