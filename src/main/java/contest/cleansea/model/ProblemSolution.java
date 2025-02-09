package contest.cleansea.model;

import lombok.val;

public class ProblemSolution {

    public final ProblemInput input;

    /*pp*/ EnumDirectionOrBase[][] gridSolution;
    public final EnumDirectionOrBase gridSolutionAt(int i, int j) {
        return gridSolution[i][j];
    }

    public ProblemSolution(ProblemInput input, EnumDirectionOrBase[][] gridSolution) {
        this.input = input;
        this.gridSolution = gridSolution;
    }

    public State toInitialState() {
        return new State(this);
    }

    public int evalScore() {
        val state = new State(this);
        int steps = state.input.steps;
        for(int step = 0; step < steps; step++) {
            state.step();
        }
        return state.currTotalScore;
    }


    public Base[] toBases() {
        Base[] bases = new Base[input.baseCount];
        int baseFoundCount = 0;
        for(int i = 0; i < input.rows; i++) {
            for (int j = 0; j < input.cols; j++) {
                EnumDirectionOrBase dirAt = gridSolution[i][j];
                if (dirAt.isBase()) {
                    Base solutionBase = new Base(input, i, j, dirAt);
                    bases[baseFoundCount] = solutionBase;
                    baseFoundCount++;
                }
            }
        }
        if (baseFoundCount != input.baseCount) {
            throw new IllegalStateException("invalid number of bases(=boats), expected " + input.baseCount + ", found " + baseFoundCount);
        }
        return bases;
    }

    @Override
    public String toString() {
        val sb = new StringBuilder();
        for(int i = 0; i < input.rows; i++) {
            for(int j = 0; j < input.cols; j++) {
                sb.append(gridSolution[i][j].symbol);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
