package contest.cleansea.model;

import lombok.Getter;
import lombok.val;

public class State {
    public final ProblemInput input; // = solution.input
    public final ProblemSolution solution;

    final Base[] bases;
    final BoatState[] boats;

    final boolean[][] currRemainWaste;

    @Getter
    int currTotalScore;

    public State(ProblemSolution solution) {
        this.input = solution.input;
        this.solution = solution;
        this.bases = solution.toBases();
        int baseCount = input.baseCount;
        assert bases.length == baseCount;
        this.boats = new BoatState[baseCount];
        for(int i = 0; i < baseCount; i++) {
            boats[i] = new BoatState(bases[i]);
        }
        this.currRemainWaste = input.getWasteArrayCopy();
    }

    public void step() {
        val boatCount = boats.length;
        // assume 2 boats are never on same grid cell as another boat (but can occur on base => solve at step0 !!)
        for(int boatIdx = 0; boatIdx < boatCount; boatIdx++) {
            val boat = boats[boatIdx];
            int i = boat.currI, j = boat.currJ;

            // load wastes if possible
            if (boat.currWastesCount < 2 && currRemainWaste[i][j]
                // && NOT IMPLEMENTED: boat is on same grid cell as another boat
            ) {
                currRemainWaste[i][j] = false;
                // may add: remember dayNumber of waste removal
                boat.currWastesCount++;
            }

            EnumDirectionOrBase dirOrBase = solution.gridSolution[i][j];
            if (dirOrBase.isStdCell) {
                boat.stepMove(dirOrBase.directionWhenStdCell);
            } else {
                // unload wastes, then move to boat direction
                val scoreInc = boat.currWastesCount;
                boat.currScoreByBoat += scoreInc;
                this.currTotalScore += scoreInc;
                // may add: increment base score
                boat.currWastesCount = 0;

                boat.stepMove(boat.baseDirection);
            }
        }
    }

    @Override
    public String toString() {
        int colPlus1 = input.cols + 1;
        char[] buffer = new char[input.cols * colPlus1];
        // print remain wastes
        int bufferIdx = 0;
        for(int i = 0; i < input.rows; i++) {
            for(int j = 0; j < input.cols; j++) {
                // buffer[bufferIdx++] = solution.gridSolution[i][j].symbol;
                buffer[bufferIdx++] = currRemainWaste[i][j] ? 'X' : '.';
            }
            buffer[bufferIdx++] = '\n';
        }
        // bases
        for(int baseIdx = 0; baseIdx < input.baseCount; baseIdx++) {
            Base base = bases[baseIdx];
            int i = base.i, j = base.j;
            int idx = i * colPlus1 + j;
            buffer[idx] = solution.gridSolution[i][j].symbol;
        }
        // print boats
        for(int boatIdx = 0; boatIdx < input.baseCount; boatIdx++) {
            BoatState boat = boats[boatIdx];
            int i = boat.currI, j = boat.currJ;
            int idx = i * colPlus1 + j;
            buffer[idx] = solution.gridSolution[i][j].symbol;
        }
        return new String(buffer);
    }
}
