package ru.drunkard.movestrategies;

import ru.drunkard.field.Field;
import ru.drunkard.utility.BFSNodeInfo;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

import java.util.*;

public class ShortestPathMoveStrategy implements IDirectedMoveStrategy {
    private Stack<Point> latestPath = new Stack<>();

    public DirectionVector nextMoveDirection(Point start, Point target, Field field) {
        if(latestPathIsUpToDate(field) || recountPath(start, target, field)) {
            Point nextPoint = latestPath.pop();
            DirectionVector dv = new DirectionVector(nextPoint.x - start.x, nextPoint.y - start.y);
            return dv;
        }
        return DirectionVector.zero();
    }

    private boolean latestPathIsUpToDate(Field field) {
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

    private boolean recountPath(Point start, Point target, Field field) {
        BFSNodeInfo[][] nodesInfo = createNodesInfoArray(field.getHeight(), field.getWidth());
        nodesInfo[start.y][start.x].pathLength = 0;
        nodesInfo[start.y][start.x].predecessor = start;
        Queue<Point> elems = new LinkedList<>();
        elems.add(start);
        while (!elems.isEmpty()) {
            Point cur = elems.remove();
            List<Point> adjacentVertices = getAdjacentVertices(cur, field, target);
            for(Point p : adjacentVertices) {
                if(nodesInfo[p.y][p.x].pathLength > nodesInfo[cur.y][cur.x].pathLength + 1) {
                    nodesInfo[p.y][p.x].pathLength = nodesInfo[cur.y][cur.x].pathLength + 1;
                    nodesInfo[p.y][p.x].predecessor = cur;
                    elems.add(p);
                }
            }
        }
        restorePath(nodesInfo, target);
        return !latestPath.isEmpty();
    }

    private List<Point> getAdjacentVertices(Point p, Field field, Point special) {
        List<Point> adjacentVertices = new ArrayList<>();
        Point left = new Point(p.x - 1, p.y);
        if(left.x >= 0 && (left.equalsTo(special) || field.sectorIsEmpty(left.x, left.y))) {
            adjacentVertices.add(left);
        }
        Point right = new Point(p.x + 1, p.y);
        if(right.x < field.getWidth() && (right.equalsTo(special) || field.sectorIsEmpty(right.x, right.y))) {
            adjacentVertices.add(right);
        }
        Point upper = new Point(p.x, p.y - 1);
        if(upper.y >= 0 && (upper.equalsTo(special) || field.sectorIsEmpty(upper.x, upper.y))) {
            adjacentVertices.add(upper);
        }
        Point lower = new Point(p.x, p.y + 1);
        if(lower.y < field.getHeight() && (lower.equalsTo(special) || field.sectorIsEmpty(lower.x, lower.y))) {
            adjacentVertices.add(lower);
        }
        return adjacentVertices;
    }

    private void restorePath(BFSNodeInfo[][] nodesInfo, Point target) {
        latestPath.clear();
        Point cur = target;
        if(nodesInfo[cur.y][cur.x].predecessor == null) {
            return;
        }
        while (!nodesInfo[cur.y][cur.x].predecessor.equalsTo(cur)) {
            latestPath.push(cur);
            cur = nodesInfo[cur.y][cur.x].predecessor;
        }
    }

    private BFSNodeInfo[][] createNodesInfoArray(int lines, int columns) {
        BFSNodeInfo[][] nodesInfo = new BFSNodeInfo[lines][columns];
        for(int i = 0; i < lines; i++) {
            for(int j = 0; j < columns; j++) {
                nodesInfo[i][j] = new BFSNodeInfo();
            }
        }
        return nodesInfo;
    }
}
