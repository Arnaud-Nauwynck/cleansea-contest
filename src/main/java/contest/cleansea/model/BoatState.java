package contest.cleansea.model;

public class BoatState {
    public final ProblemInput input;
    public final Base initBase;
    public final Direction baseDirection; // = initBase.direction
    public int currI;
    public int currJ;
    public int currWastesCount;
    public int currScoreByBoat;

    public BoatState(Base initBase) {
        this.input = initBase.input;
        this.initBase = initBase;
        assert initBase.direction.isBase();
        this.baseDirection = initBase.direction.toDirectionWhenBase();
        this.currI = initBase.i;
        this.currJ = initBase.j;
        this.currWastesCount = 0;
    }

    public void stepMove(Direction direction) {
        switch(direction) {
            case UP:
                currI--;
                if(currI < 0) {
                    currI = input.rows - 1;
                }
                break;
            case DOWN:
                currI++;
                if(currI >= input.rows) {
                    currI = 0;
                }
                break;
            case LEFT:
                currJ--;
                if(currJ < 0) {
                    currJ = input.cols - 1;
                }
                break;
            case RIGHT:
                currJ++;
                if (currJ >= input.cols) {
                    currJ = 0;
                }
                break;
        }
    }
}
