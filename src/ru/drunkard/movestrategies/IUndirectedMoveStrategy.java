package ru.drunkard.movestrategies;

import ru.drunkard.field.Field;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

public interface IUndirectedMoveStrategy {
    public DirectionVector nextMoveDirection(Point p, Field field);
}