package ru.drunkard.field;

import ru.drunkard.utility.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RectField extends BasicField {
    private int width;
    private int height;

    public RectField(int width, int height) {
        this.width = width;
        this.height = height;
        sectors = new ArrayList<>();
        for(int i = 0; i < height; i++) {
            List<Sector> row = new ArrayList<>();
            for(int j = 0; j < width; j++) {
                row.add(new Sector(null));
            }
            sectors.add(row);
        }
    }

    @Override
    public boolean pointIsOutOfBorders(int x, int y) {
        return x >= width || y >= height || x < 0 || y < 0;
    }

    @Override
    public List<Point> getNeighbours(Point p, Point special) {
        List<Point> adjacentVertices = new ArrayList<>();
        Point left = new Point(p.x - 1, p.y);
        if(left.x >= 0 && (left.equals(special) || sectorIsEmpty(left.x, left.y))) {
            adjacentVertices.add(left);
        }
        Point right = new Point(p.x + 1, p.y);
        if(right.x < width && (right.equals(special) || sectorIsEmpty(right.x, right.y))) {
            adjacentVertices.add(right);
        }
        Point upper = new Point(p.x, p.y - 1);
        if(upper.y >= 0 && (upper.equals(special) || sectorIsEmpty(upper.x, upper.y))) {
            adjacentVertices.add(upper);
        }
        Point lower = new Point(p.x, p.y + 1);
        if(lower.y < height && (lower.equals(special) || sectorIsEmpty(lower.x, lower.y))) {
            adjacentVertices.add(lower);
        }
        return adjacentVertices;
    }

    @Override
    public Iterator<Point> getIterator() {
        return this.new AreaIterator(new Point(0, 0), new Point(width - 1, height - 1));
    }

    @Override
    public Iterator<Point> getIterator(Point topleft, Point bottomright) {
        return this.new AreaIterator(topleft, bottomright);
    }

    private class AreaIterator implements Iterator<Point> {
        private final Point startPos;
        private Point curAbsPos;
        private final Point endPos;

        public AreaIterator(Point topleft, Point bottomright) {
            startPos = topleft;
            curAbsPos = new Point(-1, 0);
            endPos = bottomright;
        }

        public boolean hasNext() {
            return endPos.x != startPos.x + curAbsPos.x ||
                   endPos.y != startPos.y + curAbsPos.y;
        }

        public Point next() {
            if(startPos.x + curAbsPos.x == endPos.x) {
                curAbsPos.y = (curAbsPos.y + 1) % (endPos.y - startPos.y + 1);
            }
            curAbsPos.x = (curAbsPos.x + 1) % (endPos.x - startPos.x + 1);
            return new Point(startPos.x + curAbsPos.x, startPos.y + curAbsPos.y);
        }

        //not supported
        public void remove() {}
    }
}