package ru.drunkard.utility;

import ru.drunkard.field.Field;

import java.util.Iterator;

public class WatchingArea {
    public final Point startPos;
    public final int rightBorder;
    public final int bottomBorder;
    private final int size;

    private int scannedElementsNumber = 0;
    private Point currentPos;

    public WatchingArea(Field field, Point center, int radius) {
        int left = center.x - radius >= 0 ? center.x - radius : 0;
        int right = center.x + radius < field.getWidth() ? center.x + radius : field.getWidth() - 1;
        int top = center.y - radius >= 0 ? center.y - radius : 0;
        int bottom = center.y + radius < field.getHeight() ? center.y + radius : field.getHeight() - 1;
        startPos = new Point(left, top);
        rightBorder = right;
        bottomBorder = bottom;
        size = (right - left + 1) * (bottom - top + 1);
        currentPos = new Point(startPos.x, startPos.y);
    }

    public AreaIterator iterator() {
        scannedElementsNumber = 0;
        return this.new AreaIterator();
    }

    private class AreaIterator implements Iterator<Point> {
        public boolean hasNext() {
            return scannedElementsNumber != size - 1;
        }

        public Point next() {
            if(currentPos.y == bottomBorder) {
                currentPos.x = (currentPos.x + 1) % (rightBorder - startPos.x + 1) + startPos.x;
            }
            currentPos.y = (currentPos.y + 1) % (bottomBorder - startPos.y + 1);
            scannedElementsNumber += 1;
            return currentPos;
        }

        //not supported
        public void remove() {}
    }
}
