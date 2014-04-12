package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.game.GamePrinter;
import ru.drunkard.movestrategies.IDirectedMoveStrategy;
import ru.drunkard.states.movableobjstate.*;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;
import ru.drunkard.utility.WatchingArea;

public class Cop extends DirectedMovableObj implements ISeekerWithState {
    private ISeekerState cState = new SeekingState();
    private final Point policeStationPos;

    public Cop(Point startPos, IDirectedMoveStrategy ms, WatchingArea lampPostWA) {
        super(startPos, ms, lampPostWA);
        policeStationPos = new Point(startPos.x, startPos.y);
    }

    public void doActions(Field field) { cState.initActions(this, field); }

    public void waitSome(Field field) {}
    public void exitStartPos(Field field) {}
    public void seekTarget(Field field) {
        if(field.sectorIsEmpty(policeStationPos.x, policeStationPos.y)) {
            Point target = tryFindTarget(field);
            if(target != null) {
                cState = new TargetedState(target);
                field.setObjectInSector(policeStationPos.x, policeStationPos.y, this);
            }
        }
    }

    public void goToTarget(Point target, Field field) {
        DirectionVector dv = moveStrategy.nextMoveDirection(pos, target, field);
        if(dv.isZeroVector()) {
            cState = new ReturnToStartState();
            cState.initActions(this, field);
            return;
        }
        if(pos.x + dv.dx == target.x && pos.y + dv.dy == target.y) {
            field.setObjectInSector(pos.x + dv.dx, pos.y + dv.dy, null);
            cState = new ReturnToStartState();
        }
        moveInSector(dv, field);
    }

    public void returnToStartPos(Field field) {
        DirectionVector dv = moveStrategy.nextMoveDirection(pos, policeStationPos, field);
        moveInSector(dv, field);
        if(pos.x == policeStationPos.x && pos.y == policeStationPos.y) {
            cState = new EnterStartPosState();
        }
    }

    public void enterStartPos(Field field) {
        field.setObjectInSector(pos.x, pos.y, null);
        cState = new SeekingState();
        hasTarget = false;
    }

    public void visit(Drunkard drunkard) {
        if(drunkard.isFallen || drunkard.isSleeping) {
            hasTarget = true;
        }
    }
    public void visit(Column column) {}
    public void visit(Bottle bottle) {}
    public void visit(LampPost lampPost) {}
    public void visit(Cop cop) {}
    public void visit(Hobo hobo) {}

    public void accept(IFieldObj visitor) { visitor.visit(this); }
    public void accept(GamePrinter printer) { printer.visit(this); }

}
