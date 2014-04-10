package ru.drunkard.states.copstates;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.Cop;
import ru.drunkard.utility.Point;

public class TargetedState implements ICopsState {
    public Point target;

    public TargetedState(Point t) { target = t; }
    public void doActions(Cop cop, Field field) {
        cop.GoForDrunkard(target, field);
    }
}
