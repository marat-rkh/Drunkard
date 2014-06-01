package ru.drunkard.field;

import ru.drunkard.utility.Point;

import java.util.ArrayList;
import java.util.List;

public class HexField extends BasicField {

    public HexField(int width, int height) {
        super(width, height);
    }

//    public HexField(int width, int height) {
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
        if(inOddRow(p.y)) {
            Point topleft = new Point(p.x - 1, p.y + 1);
            tryAddValidNeighbour(topleft, neighbours);
            Point bottomleft = new Point(p.x - 1, p.y - 1);
            tryAddValidNeighbour(bottomleft, neighbours);
        } else {
            Point topright = new Point(p.x + 1, p.y + 1);
            tryAddValidNeighbour(topright, neighbours);
            Point bottomright = new Point(p.x + 1, p.y - 1);
            tryAddValidNeighbour(bottomright, neighbours);
        }
        return neighbours;
    }

    private boolean inOddRow(int y) { return y % 2 != 0; }

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
//                    endPos.y != startPos.y + curAbsPos.y;
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
//    public HexField(int width, int height) {
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
//
//    @Override
//    public List<Point> getAllNeighbours(Point p) {
//        List<Point> neighbours = new ArrayList<>();
//        neighbours.add(new Point(p.x - 1, p.y));
//        neighbours.add(new Point(p.x + 1, p.y));
//        neighbours.add(new Point(p.x, p.y - 1));
//        neighbours.add(new Point(p.x, p.y + 1));
//        if(inOddRow(p.y)) {
//            neighbours.add(new Point(p.x - 1, p.y + 1));
//            neighbours.add(new Point(p.x - 1, p.y - 1));
//        } else {
//            neighbours.add(new Point(p.x + 1, p.y + 1));
//            neighbours.add(new Point(p.x + 1, p.y - 1));
//        }
//        return neighbours;
//    }
//
//    @Override
//    public List<Point> getFreeNeighbours(Point p, Point special) {
//        List<Point> allNeighbours = getAllNeighbours(p);
//        List<Point> freeNeighbours = new ArrayList<>();
//        for(Point n: allNeighbours) {
//            tryAddNeighbour(n, special, freeNeighbours);
//        }
//        return freeNeighbours;
//    }
//
//    private void tryAddNeighbour(Point p, Point special, List<Point> neighbours) {
//        if(!pointIsOutOfBorders(p) && (p.equals(special) || sectorIsEmpty(p.x, p.y))) {
//            neighbours.add(p);
//        }
//    }
//
//    private boolean inOddRow(int y) { return y % 2 != 0; }
//
//    @Override
//    public Iterator<Point> getIterator(Point topleft, Point bottomright) {
//        return this.new AreaIterator(topleft, bottomright);
//    }
//
//    @Override
//    public Iterator<Point> getIterator() {
//        Point endPos;
//        if(inOddRow(height)) {
//            endPos = new Point(width - 2, height - 1);
//        } else {
//            endPos = new Point(width - 1, height - 1);
//        }
//        return this.new AreaIterator(new Point(0, 0), new Point(width - 1, height - 1));
//    }
//
//    private class AreaIterator implements Iterator<Point> {
//        private final Point startPos;
//        private Point curAbsPos;
//        private final Point endPos;
////        private final int xOddLimit;
////        private final int xEvenLimit;
//
//        public AreaIterator(Point topleft, Point bottomright) {
//            startPos = topleft;
//            curAbsPos = new Point(-1, 0);
//            endPos = bottomright;
////            if(inOddRow(endPos.y)) {
////                xOddLimit = endPos.x;
////                xEvenLimit = endPos.x - 1;
////            } else {
////                xOddLimit = endPos.x + 1;
////                xEvenLimit = endPos.x;
////            }
//        }
//
//        public boolean hasNext() {
//            return endPos.x != startPos.x + curAbsPos.x ||
//                   endPos.y != startPos.y + curAbsPos.y;
//        }
//
//        public Point next() {
////            if(inOddRow(startPos.y + curAbsPos.y)) {
////                if (startPos.x + curAbsPos.x == xOddLimit) {
////                    curAbsPos.y = (curAbsPos.y + 1) % (endPos.y - startPos.y + 1);
////                }
////                curAbsPos.x = (curAbsPos.x + 1) % (xOddLimit - startPos.x + 1);
////            } else {
////                if (startPos.x + curAbsPos.x == xEvenLimit) {
////                    curAbsPos.y = (curAbsPos.y + 1) % (endPos.y - startPos.y);
////                }
////                curAbsPos.x = (curAbsPos.x + 1) % (xEvenLimit - startPos.x + 1);
////            }
////            return new Point(startPos.x + curAbsPos.x, startPos.y + curAbsPos.y);
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
