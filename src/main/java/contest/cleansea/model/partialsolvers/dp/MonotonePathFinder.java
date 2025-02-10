package contest.cleansea.model.partialsolvers.dp;

import contest.cleansea.model.Direction;

public class MonotonePathFinder {

    static class MonotonePathResult {
        int maxReward;
        Direction[] path;

        public MonotonePathResult(int maxReward, Direction[] path) {
            this.maxReward = maxReward;
            this.path = path;
        }
    }

    /**
     * Computes with dynamic programming the maximum reward path from the top-left corner (0,0)
     * to the bottom-right corner (rows-1, cols-1) of the grid, using only right and down moves.
     */
    public static MonotonePathResult maxRewardPath_RightDown(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // dp[i][j] will store the maximum reward possible to reach cell (i, j)
        int[][] dp = new int[rows][cols];
        // parent[i][j] will store the move taken to reach (i, j) from its parent.
        Direction[][] parent = new Direction[rows][cols];

        // Initialize the starting cell.
        dp[0][0] = grid[0][0];
        parent[0][0] = null;

        // Initialize the first row: can only come from the left.
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
            parent[0][j] = Direction.RIGHT;
        }
        // Initialize the first column: can only come from above.
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
            parent[i][0] = Direction.DOWN;
        }

        // Fill rest of dp table
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                // Choose the best option: coming from the top or left.
                int reward_ij = grid[i][j];
                if (dp[i - 1][j] >= dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j] + reward_ij;
                    parent[i][j] = Direction.DOWN;
                } else {
                    dp[i][j] = dp[i][j - 1] + reward_ij;
                    parent[i][j] = Direction.RIGHT;
                }
            }
        }

        // Reconstruct the path from end using the parent array.
        int pathLen = rows-1 + cols-1;
        Direction[] path = new Direction[pathLen];
        int currPathIdx = pathLen-1;
        int i = rows - 1, j = cols - 1;
        while (i != 0 || j != 0) {
            Direction move = parent[i][j];
            path[currPathIdx--] = move;
            if (move == Direction.DOWN) {
                i--;
            } else if (move == Direction.RIGHT) {
                j--;
            }
        }

        return new MonotonePathResult(dp[rows - 1][cols - 1], path);
    }


    /**
     * Computes with dynamic programming the maximum reward path from the bottom-left corner (row-1,0)
     * to the top-right corner (0, cols-1) of the grid, using only right and up moves.
     */
    public static MonotonePathResult maxRewardPath_RightUp(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // dp[i][j] will store the maximum reward possible to reach cell (i, j)
        int[][] dp = new int[rows][cols];
        // parent[i][j] will store the move taken to reach (i, j) from its parent.
        Direction[][] parent = new Direction[rows][cols];

        // Initialize the starting cell.
        dp[0][0] = grid[0][0];
        parent[0][0] = null;

        // Initialize the bottom row (row index = rows - 1).
        for (int j = 1; j < cols; j++) {
            dp[rows - 1][j] = dp[rows - 1][j - 1] + grid[rows - 1][j];
            parent[rows - 1][j] = Direction.RIGHT;
        }

        // Initialize the leftmost column (column 0) for rows above the bottom.
        for (int i = rows - 2; i >= 0; i--) {
            dp[i][0] = dp[i + 1][0] + grid[i][0];
            parent[i][0] = Direction.UP;
        }

        // Fill rest of dp table
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 1; j < cols; j++) {
                int fromLeft = dp[i][j - 1];
                int fromBelow = dp[i + 1][j];

                if (fromLeft >= fromBelow) {
                    dp[i][j] = fromLeft + grid[i][j];
                    parent[i][j] = Direction.RIGHT;
                } else {
                    dp[i][j] = fromBelow + grid[i][j];
                    parent[i][j] = Direction.UP;
                }
            }
        }

        // Reconstruct the path from end using the parent array.
        int pathLen = rows-1 + cols-1;
        Direction[] path = new Direction[pathLen];
        int currPathIdx = pathLen-1;
        int i = 0, j = cols - 1;
        while (i != 0 || j != 0) {
            Direction move = parent[i][j];
            path[currPathIdx--] = move;
            if (move == Direction.UP) {
                i++;
            } else if (move == Direction.RIGHT) {
                j--;
            }
        }

        return new MonotonePathResult(dp[0][cols - 1], path);
    }

}

