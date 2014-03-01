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

    protected MovableObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    protected boolean outOfBorders(DirectionVector directionVector, Field field) {
        int new_x = x + directionVector.dx;
        int new_y = y + directionVector.dy;
        return new_x >= field.getWidth() || new_y >= field.getHeight() || new_x < 0 || new_y < 0;
    }

    protected boolean sectorIsEmpty(DirectionVector directionVector, Field field) {
        return field.sectorIsEmpty(x + directionVector.dx, y + directionVector.dy);
    }

    public void moveInSector(DirectionVector directionVector, Field field) {
        field.setObjectInSector(x, y, null);
        field.setObjectInSector(x + directionVector.dx, y + directionVector.dy, this);
        x += directionVector.dx;
        y += directionVector.dy;
    }
}
