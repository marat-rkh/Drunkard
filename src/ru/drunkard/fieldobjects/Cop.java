package ru.drunkard.fieldobjects;

import ru.drunkard.field.GameField;
import ru.drunkard.gameprinters.GamePrinter;
import ru.drunkard.movestrategies.IDirectedMoveStrategy;
import ru.drunkard.states.movableobjstate.*;
import ru.drunkard.utility.Point;

public class Cop extends DirectedMovableObj implements ISeekerWithState {
    private ISeekerState cState = new SeekingState();
    private final Point policeStationPos;

    public Cop(Point startPos, IDirectedMoveStrategy ms, Point searchAreaStart, Point searchAreaEnd) {
        super(startPos, ms, searchAreaStart, searchAreaEnd);
        policeStationPos = new Point(startPos.x, startPos.y);
    }

    public void doActions(GameField field) { cState.initActions(this, field); }

    public void waitSome(GameField field) {}
    public void exitStartPos(GameField field) {}
    public void seekTarget(GameField field) {
        if(field.sectorIsEmpty(policeStationPos.x, policeStationPos.y)) {
            Point target = tryFindTarget(field);
            if(target != null) {
                cState = new TargetedState(target);
                field.setObjectInSector(policeStationPos.x, policeStationPos.y, this);
            }
        }
    }

    public void goToTarget(Point target, GameField field) {
        Point nextPos = moveStrategy.nextPosition(pos, target, field);
        if(nextPos.equals(pos)) {
            cState = new ReturnToStartState();
            cState.initActions(this, field);
            return;
        }
        if(nextPos.x == target.x && nextPos.y == target.y) {
            field.setObjectInSector(nextPos.x, nextPos.y, null);
            cState = new ReturnToStartState();
        }
        moveInSector(nextPos, field);
    }

    public void returnToStartPos(GameField field) {
        Point nextPos = moveStrategy.nextPosition(pos, policeStationPos, field);
        moveInSector(nextPos, field);
        if(pos.x == policeStationPos.x && pos.y == policeStationPos.y) {
            cState = new EnterStartPosState();
        }
    }

    public void enterStartPos(GameField field) {
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
