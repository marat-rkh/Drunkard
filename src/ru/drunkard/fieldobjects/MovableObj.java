package ru.drunkard.fieldobjects;

import ru.drunkard.field.GameField;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

abstract public class MovableObj implements IFieldObj {
    protected Point pos;

    protected MovableObj(Point pos) { this.pos = pos; }

    protected boolean sectorIsEmpty(DirectionVector directionVector, GameField field) {
        return field.sectorIsEmpty(pos.x + directionVector.dx, pos.y + directionVector.dy);
    }

    public void moveInSector(DirectionVector directionVector, GameField field) {
        field.setObjectInSector(pos.x, pos.y, null);
        field.setObjectInSector(pos.x + directionVector.dx, pos.y + directionVector.dy, this);
        pos.x += directionVector.dx;
        pos.y += directionVector.dy;
    }
}
