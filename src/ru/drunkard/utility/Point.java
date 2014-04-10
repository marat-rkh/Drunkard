package ru.drunkard.utility;

/**
 * Created by mr_ito on 4/9/14.
 */
public class Point {
    public int x;
    public int y;
    public Point(int x, int y) { this.x = x; this.y = y; }

    public boolean equalsTo(Point other) {
        return x == other.x && y == other.y;
    }
}
