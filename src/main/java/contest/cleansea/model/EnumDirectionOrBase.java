package contest.cleansea.model;

public enum EnumDirectionOrBase {
    UP('^', true),
    DOWN('v', true),
    LEFT('<', true),
    RIGHT('>', true),
    BASE_UP('N', false),
    BASE_DOWN('S', false),
    BASE_LEFT('W', false),
    BASE_RIGHT('E', false);

    public final char symbol;
    public final boolean isStdCell;
    public final Direction directionWhenStdCell;

    EnumDirectionOrBase(char symbol, boolean isStdCell) {
        this.symbol = symbol;
        this.isStdCell = isStdCell;
        switch (symbol) {
            case '^':
                this.directionWhenStdCell = Direction.UP;
                break;
            case 'v':
                this.directionWhenStdCell = Direction.DOWN;
                break;
            case '<':
                this.directionWhenStdCell = Direction.LEFT;
                break;
            case '>':
                this.directionWhenStdCell = Direction.RIGHT;
                break;
            default:
                this.directionWhenStdCell = null;
                break;
        }
    }

    public boolean isBase() {
        return !isStdCell;
    }

    public Direction toDirectionWhenBase() {
        assert isBase();
        switch (this) {
            case BASE_UP:
                return Direction.UP;
            case BASE_DOWN:
                return Direction.DOWN;
            case BASE_LEFT:
                return Direction.LEFT;
            case BASE_RIGHT:
                return Direction.RIGHT;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction toDirectionOrBoatDirection(Direction boatDirection) {
        switch (this) {
            case UP:
                return Direction.UP;
            case DOWN:
                return Direction.DOWN;
            case LEFT:
                return Direction.LEFT;
            case RIGHT:
                return Direction.RIGHT;
            case BASE_UP:
            case BASE_DOWN:
            case BASE_LEFT:
            case BASE_RIGHT:
                return boatDirection;
            default:
                throw new IllegalStateException();
        }
    }

    public static EnumDirectionOrBase fromSymbol(char symbol) {
        switch (symbol) {
            case '^':
                return UP;
            case 'v':
                return DOWN;
            case '<':
                return LEFT;
            case '>':
                return RIGHT;
            case 'N':
                return BASE_UP;
            case 'S':
                return BASE_DOWN;
            case 'W':
                return BASE_LEFT;
            case 'E':
                return BASE_RIGHT;
            default:
                throw new IllegalArgumentException("Invalid symbol: " + symbol);
        }
    }
}
