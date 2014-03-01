package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.field.FieldState;
import ru.drunkard.utility.DirectionVector;

import java.util.Random;

/**
 * Created by mr_ito on 2/28/14.
 */
public class Drunkard extends MovableObj {

    private boolean isSleeping = false;
    private boolean isFallen = false;
    private Bottle bottle = new Bottle();

    private final int BOTTLE_DROP_CHANCE = 30;

    public void doActions(FieldState fieldState) {
        DirectionVector direction = generateMoveDirection();
        if(!direction.isZeroVector()) {
            int xBeforeMoveTry = x;
            int yBeforeMoveTry = y;
            if(sectorIsEmpty(direction, fieldState)) {
                moveInSector(direction, fieldState);
            }
            else {
                fieldState.sendVisitorToSector(x + direction.dx, y + direction.dy, this);
            }
            if(xBeforeMoveTry != x || yBeforeMoveTry != y) {
                tryDropBottle(xBeforeMoveTry, yBeforeMoveTry, fieldState);
            }
        }
    }

    public void accept(IFieldObj visitor) {
        visitor.visit(this);
    }

    public void visit(Drunkard otherDrunkard) {
        if(otherDrunkard.isSleeping) {
            this.isSleeping = true;
        }
    }

    public void visit(Column column) {
        isSleeping = true;
    }

    public void visit(Bottle bottle) {
        isFallen = true;
    }

    private void tryDropBottle(int x_old, int y_old, FieldState fieldState) {
        if(bottle != null) {
            Random generator = new Random();
            if(generator.nextInt(100) < BOTTLE_DROP_CHANCE) {
                fieldState.setObjectInSector(x_old, y_old, new Bottle());
                bottle = null;
            }
        }
    }
}