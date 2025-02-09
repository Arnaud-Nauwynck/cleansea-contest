package contest.cleansea.model;

/**
 * Immutable CleanSea solution Base
 */
public class Base {
    public final ProblemInput input;
    public final int i;
    public final int j;
    public final EnumDirectionOrBase direction;

    public Base(ProblemInput input, int i, int j, EnumDirectionOrBase direction) {
        this.input = input;
        this.i = i;
        this.j = j;
        this.direction = direction;
    }
}
