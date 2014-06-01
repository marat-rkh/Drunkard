package ru.drunkard.fieldobjects;

import ru.drunkard.field.GameField;
import ru.drunkard.utility.Point;

abstract public class MovableObj implements IFieldObj {
    protected Point pos;

    protected MovableObj(Point pos) { this.pos = pos; }

//    protected boolean sectorIsEmpty(DirectionVector directionVector, GameField field) {
//        return field.sectorIsEmpty(pos.x + directionVector.dx, pos.y + directionVector.dy);
//    }

    public void moveInSector(Point destinationPos, GameField field) {
        field.setObjectInSector(pos.x, pos.y, null);
        field.setObjectInSector(destinationPos.x, destinationPos.y, this);
        pos = destinationPos;
    }
}
