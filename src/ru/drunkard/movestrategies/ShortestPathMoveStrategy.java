package ru.drunkard.movestrategies;

import ru.drunkard.field.GameField;
import ru.drunkard.utility.BFSNodeInfo;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

import java.util.*;

public class ShortestPathMoveStrategy implements IDirectedMoveStrategy {
    private Stack<Point> latestPath = new Stack<>();

    public DirectionVector nextMoveDirection(Point start, Point target, GameField field) {
        if(latestPathIsUpToDate(field) || recountPath(start, target, field)) {
            Point nextPoint = latestPath.pop();
            return new DirectionVector(nextPoint.x - start.x, nextPoint.y - start.y);
        }
        return DirectionVector.zero();
    }

    private boolean latestPathIsUpToDate(GameField field) {
        if(latestPath.isEmpty()) {
            return false;
        }
        for(Point p : latestPath) {
            if(!field.sectorIsEmpty(p.x, p.y)) {
                return false;
            }
        }
        return true;
    }

    private boolean recountPath(Point start, Point target, GameField field) {
        Map<Point, BFSNodeInfo> nodesInfo = new HashMap<>();
        nodesInfo.put(start, new BFSNodeInfo(start, 0));
        Queue<Point> elems = new LinkedList<>();
        elems.add(start);
        while (!elems.isEmpty()) {
            Point current = elems.remove();
            List<Point> neighbours = field.getNeighbours(current, target);
            for(Point neighbour : neighbours) {
                BFSNodeInfo neighbourInfo = nodesInfo.get(neighbour);
                BFSNodeInfo currentInfo = nodesInfo.get(current);
                if(neighbourInfo == null || neighbourInfo.pathLength > currentInfo.pathLength + 1) {
                    nodesInfo.put(neighbour, new BFSNodeInfo(current, currentInfo.pathLength + 1));
                    elems.add(neighbour);
                }
            }
        }
        restorePath(nodesInfo, target);
        return !latestPath.isEmpty();
    }

//    private List<Point> getAdjacentVertices(Point p, GameField field, Point special) {
//        List<Point> adjacentVertices = new ArrayList<>();
//        Point left = new Point(p.x - 1, p.y);
//        if(left.x >= 0 && (left.equals(special) || field.sectorIsEmpty(left.x, left.y))) {
//            adjacentVertices.add(left);
//        }
//        Point right = new Point(p.x + 1, p.y);
//        if(right.x < field.getWidth() && (right.equals(special) || field.sectorIsEmpty(right.x, right.y))) {
//            adjacentVertices.add(right);
//        }
//        Point upper = new Point(p.x, p.y - 1);
//        if(upper.y >= 0 && (upper.equals(special) || field.sectorIsEmpty(upper.x, upper.y))) {
//            adjacentVertices.add(upper);
//        }
//        Point lower = new Point(p.x, p.y + 1);
//        if(lower.y < field.getHeight() && (lower.equals(special) || field.sectorIsEmpty(lower.x, lower.y))) {
//            adjacentVertices.add(lower);
//        }
//        return adjacentVertices;
//    }

    private void restorePath(Map<Point, BFSNodeInfo> nodesInfo, Point target) {
        latestPath.clear();
        Point current = target;
        if(nodesInfo.get(current).predecessor == null) {
            return;
        }
        BFSNodeInfo currentInfo = nodesInfo.get(current);
        while (!currentInfo.predecessor.equals(current)) {
            latestPath.push(current);
            current = currentInfo.predecessor;
            currentInfo = nodesInfo.get(current);
        }
    }

//    private BFSNodeInfo[][] createNodesInfoArray(int lines, int columns) {
//        BFSNodeInfo[][] nodesInfo = new BFSNodeInfo[lines][columns];
//        for(int i = 0; i < lines; i++) {
//            for(int j = 0; j < columns; j++) {
//                nodesInfo[i][j] = new BFSNodeInfo();
//            }
//        }
//        return nodesInfo;
//    }
}
