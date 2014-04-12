package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.movestrategies.IDirectedMoveStrategy;
import ru.drunkard.utility.FieldArea;
import ru.drunkard.utility.Point;

import java.util.Iterator;

abstract public class DirectedMovableObj extends MovableObj {
    protected IDirectedMoveStrategy moveStrategy;
    protected FieldArea fieldArea;
    protected boolean hasTarget = false;

    protected DirectedMovableObj(Point pos, IDirectedMoveStrategy ms, FieldArea wa) {
        super(pos);
        moveStrategy = ms;
        fieldArea = wa;
    }

    protected Point tryFindTarget(Field field) {
        Iterator<Point> iter = fieldArea.iterator();
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
