package ru.drunkard.utility;

/**
 * Created by mr_ito on 3/1/14.
 */
public class DirectionVector {

    public int dx = 0;
    public int dy = 0;

    public DirectionVector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public boolean isZeroVector() {
        return dx == 0 && dy == 0;
    }
}
