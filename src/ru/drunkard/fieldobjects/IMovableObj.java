package ru.drunkard.fieldobjects;

import ru.drunkard.field.FieldState;
import ru.drunkard.utility.DirectionVector;

/**
 * Created by mr_ito on 2/28/14.
 */
public interface IMovableObj extends IFieldObj {

    public DirectionVector generateMoveDirection();
    public void moveInSector(DirectionVector directionVector, FieldState fieldState);
}
