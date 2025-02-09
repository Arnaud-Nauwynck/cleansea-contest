package contest.cleansea.model;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;



    public void movePoint(MutablePoint point, int rows, int cols) {
        switch (this) {
            case UP:
                point.i--;
                if(point.i < 0) {
                    point.i = rows - 1;
                }
                break;
            case DOWN:
                point.i++;
                if(point.i >= rows) {
                    point.i = 0;
                }
                break;
            case LEFT:
                point.j--;
                if(point.j < 0) {
                    point.j = cols - 1;
                }
                break;
            case RIGHT:
                point.j++;
                if (point.j >= cols) {
                    point.j = 0;
                }
                break;
        }
    }


    public int movePointX(int i, int rows) {
        switch (this) {
            case UP:
                i--;
                if(i < 0) {
                    i = rows - 1;
                }
                break;
            case DOWN:
                i++;
                if(i >= rows) {
                    i = 0;
                }
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
        }
        return i;
    }

    public int movePointY(int j, int cols) {
        switch (this) {
            case UP:
                break;
            case DOWN:
                break;
            case LEFT:
                j--;
                if(j < 0) {
                    j = cols - 1;
                }
                break;
            case RIGHT:
                j++;
                if (j >= cols) {
                    j = 0;
                }
                break;
        }
        return j;
    }

}