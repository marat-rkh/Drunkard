package ru.drunkard.fieldobjects;

import ru.drunkard.utility.DirectionVector;

/**
 * Created by mr_ito on 2/28/14.
 */
public class Drunkard extends ActiveObj {

    private boolean isSleeping = false;
    private boolean isFallen = false;
    private Bottle bottle = new Bottle();

    public void doActions(FieldState fieldState) {
        DirectionVector direction = generateMoveDirection();
        if(!direction.isZeroVector()) {
            if(sectorIsEmpty(direction, fieldState)) {
                moveInSector(direction, fieldState);
            }
            else {
                fieldState
            }
        }
    }

    public void accept(Drunkard otherDrunkard) {
        if(this.isSleeping) {
            otherDrunkard.sendToSleep();
        }
    }

    public void sendToSleep() {
        isSleeping = true;
    }

    public void makeFallen() {
        isFallen = true;
    }
}