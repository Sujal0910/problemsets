class Solution {
    public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {
        int[][] temp = new int[n][n];

        // Shift rows left
        for (int r = 0; r < n; r++) {
            int shift = rowShift[r];
            for (int c = 0; c < n; c++) {
                int newCol = (c - shift + n) % n;
                temp[r][newCol] = grid[r][c];
            }
        }

        int[][] res = new int[n][n];

        // Shift columns up
        for (int c = 0; c < n; c++) {
            int shift = colShift[c];
            for (int r = 0; r < n; r++) {
                int newRow = (r - shift + n) % n;
                res[newRow][c] = temp[r][c];
            }
        }

        return res;
    }
}