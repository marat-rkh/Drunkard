package ru.drunkard.fieldobjects;

import ru.drunkard.utility.DirectionVector;

/**
 * Created by mr_ito on 2/28/14.
 */
public interface IActiveObj extends IFieldObj {

    public DirectionVector generateMoveDirection();
    public void moveInSector(DirectionVector directionVector, FieldState fieldState);
}
