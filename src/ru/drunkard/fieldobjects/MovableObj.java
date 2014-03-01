package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.utility.DirectionVector;

import java.util.Random;

/**
 * Created by mr_ito on 3/1/14.
 */
abstract public class MovableObj implements IMovableObj {

    protected int x;
    protected int y;

    public DirectionVector generateMoveDirection() {
        Random generator = new Random();
        if(generator.nextInt(2) == 0) {
            int dx = generator.nextInt(2) == 0 ? -1 : 1;
            return new DirectionVector(dx, 0);
        } else {
            int dy = generator.nextInt(2) == 0 ? -1 : 1;
            return new DirectionVector(0, dy);
        }
    }

    public void moveInSector(DirectionVector directionVector, Field field) {
        field.setObjectInSector(x, y, null);
        field.setObjectInSector(x + directionVector.dx, y + directionVector.dy, this);
        x += directionVector.dx;
        y += directionVector.dy;
    }

    public boolean sectorIsEmpty(DirectionVector directionVector, Field field) {
        return field.sectorIsEmpty(x + directionVector.dx, y + directionVector.dy);
    }

    public boolean outOfBorders(DirectionVector directionVector, Field field) {
        return (x + directionVector.dx >= field.getWidth()) && (y + directionVector.dy >= field.getHeight());
    }
}
