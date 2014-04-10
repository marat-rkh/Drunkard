package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.game.GamePrinter;
import ru.drunkard.movestrategies.IDirectedMoveStrategy;
import ru.drunkard.states.copstates.*;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;
import ru.drunkard.utility.WatchingArea;

import java.util.Iterator;

public class Cop extends DirectedMovableObj {
    private ICopsState wState = new SearchForDrunkardState();
    private WatchingArea lampPostWatchingArea;
    private final Point policeStationPos;

    public boolean hasGotDrunkard = false;

    public Cop(Point startPos, IDirectedMoveStrategy ms, WatchingArea lampPostWA) {
        super(startPos, ms);
        policeStationPos = new Point(startPos.x, startPos.y);
        lampPostWatchingArea = lampPostWA;
    }

    public void doActions(Field field) {
        wState.doActions(this, field);
    }

    public void SearchForDrunkard(Field field) {
        if(field.sectorIsEmpty(policeStationPos.x, policeStationPos.y)) {
            Point target = tryFindDrunkard(field);
            if(target != null) {
                wState = new TargetedState(target);
                field.setObjectInSector(policeStationPos.x, policeStationPos.y, this);
            }
        }
    }

    public void GoForDrunkard(Point target, Field field) {
        DirectionVector dv = moveStrategy.nextMoveDirection(pos, target, field);
        if(dv.isZeroVector()) {
            wState = new GoBackState();
            wState.doActions(this, field);
            return;
        }
        if(pos.x + dv.dx == target.x && pos.y + dv.dy == target.y) {
            field.setObjectInSector(pos.x + dv.dx, pos.y + dv.dy, null);
            wState = new GoBackState();
        }
        moveInSector(dv, field);
    }

    public void ReturnToStation(Field field) {
        DirectionVector dv = moveStrategy.nextMoveDirection(pos, policeStationPos, field);
        moveInSector(dv, field);
        if(pos.x == policeStationPos.x && pos.y == policeStationPos.y) {
            wState = new EnteringStationState();
        }
    }

    public void EnterTheStation(Field field) {
        field.setObjectInSector(pos.x, pos.y, null);
        wState = new SearchForDrunkardState();
        hasGotDrunkard = false;
    }


    private Point tryFindDrunkard(Field field) {
        Iterator<Point> iter = lampPostWatchingArea.iterator();
        while(iter.hasNext()) {
            Point current = iter.next();
            field.sendVisitorToSector(current.x, current.y, this);
            if(hasGotDrunkard) {
                return current;
            }
        }
        return null;
    }

    public void visit(Drunkard drunkard) {
        if(drunkard.isFallen || drunkard.isSleeping) {
            hasGotDrunkard = true;
        }
    }
    public void visit(Column column) {}
    public void visit(Bottle bottle) {}
    public void visit(LampPost lampPost) {}
    public void visit(Cop cop) {}

    public void accept(IFieldObj visitor) { visitor.visit(this); }
    public void accept(GamePrinter printer) { printer.visit(this); }

}
