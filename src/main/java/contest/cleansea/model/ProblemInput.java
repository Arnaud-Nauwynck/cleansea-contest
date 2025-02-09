package contest.cleansea.model;

import lombok.Getter;
import lombok.val;

/**
 * Immutable CleanSea problem input
 */
public class ProblemInput {
    public static final int DEFAULT_STEPS = 1000;

    @Getter
    public final int steps;

    @Getter
    public final int rows;
    @Getter
    public final int cols;
    @Getter
    public final int baseCount;

    private final boolean wasteAt[][];

    public ProblemInput(int steps, int rows, int cols, int boatsCount, boolean[][] wasteAt) {
        this.steps = steps;
        this.rows = wasteAt.length;
        this.cols = wasteAt[0].length;
        this.baseCount = boatsCount;
        this.wasteAt = wasteAt;
    }

    public boolean isWasteAt(int i, int j) {
        return wasteAt[i][j];
    }

    public boolean[][] getWasteArrayCopy() {
        boolean[][] res = new boolean[rows][cols];
        for(int i = 0; i < rows; i++) {
            res[i] = wasteAt[i].clone();
        }
        return res;
    }

    @Override
    public String toString() {
        val sb = new StringBuilder();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                sb.append(wasteAt[i][j] ? 'X' : '.');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
