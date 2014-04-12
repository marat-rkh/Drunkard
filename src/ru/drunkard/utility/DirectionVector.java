package ru.drunkard.utility;

public class DirectionVector {
    public int dx = 0;
    public int dy = 0;

    public DirectionVector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public boolean isZeroVector() { return dx == 0 && dy == 0; }
    public static DirectionVector zero() { return new DirectionVector(0, 0); }
}
