package ru.drunkard.field;

import ru.drunkard.utility.Point;

import java.util.ArrayList;
import java.util.List;

public class RectField extends BasicField {

    public RectField(int width, int height) {
        super(width, height);
    }

//    public RectField(int width, int height) {
//        super(width, height);
//        sectors = new ArrayList<>();
//        for(int i = 0; i < height; i++) {
//            List<Sector> row = new ArrayList<>();
//            for(int j = 0; j < width; j++) {
//                row.add(new Sector(null));
//            }
//            sectors.add(row);
//        }
//    }
//
//    @Override
//    public boolean pointIsOutOfBorders(Point p) {
//        return p.x >= width || p.y >= height || p.x < 0 || p.y < 0;
//    }

    @Override
    public List<Point> getAllNeighbours(Point p) {
        List<Point> neighbours = new ArrayList<>();
        Point left = new Point(p.x - 1, p.y);
        tryAddValidNeighbour(left, neighbours);
        Point right = new Point(p.x + 1, p.y);
        tryAddValidNeighbour(right, neighbours);
        Point upper = new Point(p.x, p.y - 1);
        tryAddValidNeighbour(upper, neighbours);
        Point lower = new Point(p.x, p.y + 1);
        tryAddValidNeighbour(lower, neighbours);
        return neighbours;
    }

//    @Override
//    public List<Point> getFreeNeighbours(Point p, Point special) {
//        List<Point> adjacentVertices = getAllNeighbours(p);
//        List<Point> neighbours = new ArrayList<>();
//        for(Point ap : adjacentVertices) {
//            tryAddFreeNeighbour(ap, special, neighbours);
//        }
//        return neighbours;
//    }
//
//    private void tryAddValidNeighbour(Point p, List<Point> neighbours) {
//        if(!pointIsOutOfBorders(p)) {
//            neighbours.add(p);
//        }
//    }
//
//    private void tryAddFreeNeighbour(Point p, Point special, List<Point> neighbours) {
//        if(p.equals(special) || sectorIsEmpty(p.x, p.y)) {
//            neighbours.add(p);
//        }
//    }
//
//    @Override
//    public Iterator<Point> getIterator() {
//        return this.new AreaIterator(new Point(0, 0), new Point(width - 1, height - 1));
//    }
//
//    @Override
//    public Iterator<Point> getIterator(Point topleft, Point bottomright) {
//        return this.new AreaIterator(topleft, bottomright);
//    }
//
//    private class AreaIterator implements Iterator<Point> {
//        private final Point startPos;
//        private Point curAbsPos;
//        private final Point endPos;
//
//        public AreaIterator(Point topleft, Point bottomright) {
//            startPos = topleft;
//            curAbsPos = new Point(-1, 0);
//            endPos = bottomright;
//        }
//
//        public boolean hasNext() {
//            return endPos.x != startPos.x + curAbsPos.x ||
//                   endPos.y != startPos.y + curAbsPos.y;
//        }
//
//        public Point next() {
//            if(startPos.x + curAbsPos.x == endPos.x) {
//                curAbsPos.y = (curAbsPos.y + 1) % (endPos.y - startPos.y + 1);
//            }
//            curAbsPos.x = (curAbsPos.x + 1) % (endPos.x - startPos.x + 1);
//            return new Point(startPos.x + curAbsPos.x, startPos.y + curAbsPos.y);
//        }
//
//        //not supported
//        public void remove() {}
//    }
}