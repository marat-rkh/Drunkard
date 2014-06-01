package ru.drunkard.movestrategies;

import ru.drunkard.field.GameField;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

public interface IDirectedMoveStrategy {
    public DirectionVector nextMoveDirection(Point start, Point target, GameField field);
}
