package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.game.GamePrinter;
import ru.drunkard.movestrategies.IDirectedMoveStrategy;
import ru.drunkard.states.movableobjstate.*;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.FieldArea;
import ru.drunkard.utility.Point;

public class Hobo extends DirectedMovableObj implements ISeekerWithState {
    private ISeekerState hState = new WaitingState();
    private final Point glassStationPos;
    private int timer = 30;

    public Hobo(Point startPos, IDirectedMoveStrategy ms, FieldArea wa) {
        super(startPos, ms, wa);
        glassStationPos = new Point(startPos.x, startPos.y);
    }

    public void doActions(Field field) { hState.initActions(this, field); }
    public void waitSome(Field field) {
        if(timer == 0) {
            hState = new ExitStartPosState();
            hState.initActions(this, field);
        } else {
            timer -= 1;
        }
    }
    public void exitStartPos(Field field) {
        if(field.sectorIsEmpty(glassStationPos.x, glassStationPos.y)) {
            field.setObjectInSector(glassStationPos.x, glassStationPos.y, this);
            hState = new SeekingState();
        }
    }

    public void seekTarget(Field field) {
        Point target = tryFindTarget(field);
        if(target != null) {
            hState = new TargetedState(target);
            hState.initActions(this, field);
        }
    }
    public void goToTarget(Point target, Field field) {
        DirectionVector dv = moveStrategy.nextMoveDirection(pos, target, field);
        if(pos.x + dv.dx == target.x && pos.y + dv.dy == target.y) {
            field.setObjectInSector(pos.x + dv.dx, pos.y + dv.dy, null);
            hState = new ReturnToStartState();
        }
        moveInSector(dv, field);
    }
    public void returnToStartPos(Field field) {
        DirectionVector dv = moveStrategy.nextMoveDirection(pos, glassStationPos, field);
        moveInSector(dv, field);
        if(pos.x == glassStationPos.x && pos.y == glassStationPos.y) {
            hState = new EnterStartPosState();
        }
    }
    public void enterStartPos(Field field) {
        field.setObjectInSector(pos.x, pos.y, null);
        hState = new WaitingState();
        hasTarget = false;
        timer = 30;
    }
    public void visit(Drunkard drunkard) {}
    public void visit(Column column) {}
    public void visit(Bottle bottle) { hasTarget = true; }
    public void visit(LampPost lampPost) {}
    public void visit(Cop cop) {}
    public void visit(Hobo hobo) {}

    public void accept(IFieldObj visitor) { visitor.visit(this); }
    public void accept(GamePrinter printer) { printer.visit(this); }
}
