package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.game.GamePrinter;
import ru.drunkard.utility.DirectionVector;

import java.util.Random;

/**
 * Created by mr_ito on 2/28/14
 */
public class Drunkard extends MovableObj {

    public boolean isSleeping = false;
    public boolean isFallen = false;
    private Bottle bottle = new Bottle();

    private final double BOTTLE_DROP_CHANCE = 1.0 / 30;

    public Drunkard(int x, int y) {
        super(x, y);
    }

    public void doActions(Field field) {
        if(!isFallen && !isSleeping) {
            DirectionVector direction = generateMoveDirection();
            if(!outOfBorders(direction, field)) {
                int xBeforeMoveTry = x;
                int yBeforeMoveTry = y;
                if(sectorIsEmpty(direction, field)) {
                    moveInSector(direction, field);
                }
                else {
                    field.sendVisitorToSector(x + direction.dx, y + direction.dy, this);
                }
                if(xBeforeMoveTry != x || yBeforeMoveTry != y) {
                    tryDropBottle(xBeforeMoveTry, yBeforeMoveTry, field);
                }
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

    public void accept(GamePrinter printer) {
        printer.visit(this);
    }

    private void tryDropBottle(int x_old, int y_old, Field field) {
        if(bottle != null) {
            if(Math.random() < BOTTLE_DROP_CHANCE) {
                Bottle bottleCopy = new Bottle();
                field.setObjectInSector(x_old, y_old, bottleCopy);
                bottle = null;
                field.addNewObject(bottleCopy);
            }
        }
    }
}