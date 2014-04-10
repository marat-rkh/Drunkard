package ru.drunkard.fieldobjects;

import ru.drunkard.movestrategies.IDirectedMoveStrategy;
import ru.drunkard.utility.Point;

abstract public class DirectedMovableObj extends MovableObj {
    protected IDirectedMoveStrategy moveStrategy;

    protected DirectedMovableObj(Point pos, IDirectedMoveStrategy ms) {
        super(pos);
        moveStrategy = ms;
    }
}
