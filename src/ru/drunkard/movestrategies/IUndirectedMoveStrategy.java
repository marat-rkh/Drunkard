package ru.drunkard.movestrategies;

import ru.drunkard.field.GameField;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

public interface IUndirectedMoveStrategy {
    public DirectionVector nextMoveDirection(Point p, GameField field);
}
