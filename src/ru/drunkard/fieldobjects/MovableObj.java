package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.movestrategies.IUndirectedMoveStrategy;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

abstract public class MovableObj implements IFieldObj {
    protected Point pos;

    protected MovableObj(Point pos) { this.pos = pos; }

    protected boolean sectorIsEmpty(DirectionVector directionVector, Field field) {
        return field.sectorIsEmpty(pos.x + directionVector.dx, pos.y + directionVector.dy);
    }

    public void moveInSector(DirectionVector directionVector, Field field) {
        field.setObjectInSector(pos.x, pos.y, null);
        field.setObjectInSector(pos.x + directionVector.dx, pos.y + directionVector.dy, this);
        pos.x += directionVector.dx;
        pos.y += directionVector.dy;
    }
}
