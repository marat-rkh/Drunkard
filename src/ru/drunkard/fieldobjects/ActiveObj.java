package ru.drunkard.fieldobjects;

import ru.drunkard.field.FieldState;
import ru.drunkard.utility.DirectionVector;

import java.util.Random;

/**
 * Created by mr_ito on 3/1/14.
 */
abstract public class ActiveObj implements IActiveObj {

    protected int x;
    protected int y;

    private final int RAND_INT_UPPER_BOUND = 2;

    public DirectionVector generateMoveDirection() {
        Random generator = new Random();
        return new DirectionVector(generator.nextInt(RAND_INT_UPPER_BOUND) - 1,
                generator.nextInt(RAND_INT_UPPER_BOUND) - 1);
    }

    public void moveInSector(DirectionVector directionVector, FieldState fieldState) {
        fieldState.setObjectInSector(x, y, null);
        fieldState.setObjectInSector(x + directionVector.dx, y + directionVector.dy, this);
    }

    public boolean sectorIsEmpty(DirectionVector directionVector, FieldState fieldState) {
        return fieldState.sectorIsEmpty(x + directionVector.dx, y + directionVector.dy);
    }
}
