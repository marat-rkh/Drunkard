package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.movestrategies.IDirectedMoveStrategy;
import ru.drunkard.utility.Point;
import ru.drunkard.utility.WatchingArea;

import java.util.Iterator;

abstract public class DirectedMovableObj extends MovableObj {
    protected IDirectedMoveStrategy moveStrategy;
    protected WatchingArea watchingArea;
    protected boolean hasTarget = false;

    protected DirectedMovableObj(Point pos, IDirectedMoveStrategy ms, WatchingArea wa) {
        super(pos);
        moveStrategy = ms;
        watchingArea = wa;
    }

    protected Point tryFindTarget(Field field) {
        Iterator<Point> iter = watchingArea.iterator();
        while(iter.hasNext()) {
            Point current = iter.next();
            field.sendVisitorToSector(current.x, current.y, this);
            if(hasTarget) {
                return current;
            }
        }
        return null;
    }
}
