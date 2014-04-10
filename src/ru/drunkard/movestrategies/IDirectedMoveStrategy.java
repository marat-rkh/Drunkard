package ru.drunkard.movestrategies;

import ru.drunkard.field.Field;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

public interface IDirectedMoveStrategy {
    public DirectionVector nextMoveDirection(Point start, Point target, Field field);
}
