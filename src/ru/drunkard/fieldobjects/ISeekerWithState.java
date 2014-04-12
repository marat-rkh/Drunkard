package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.utility.Point;

public interface ISeekerWithState {
    public void waitSome(Field field);
    public void exitStartPos(Field field);
    public void seekTarget(Field field);
    public void goToTarget(Point target, Field field);
    public void returnToStartPos(Field field);
    public void enterStartPos(Field field);
}
