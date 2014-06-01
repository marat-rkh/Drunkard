package ru.drunkard.movestrategies;

import ru.drunkard.field.GameField;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

import java.util.Random;

public class DrunkardMoveStrategy implements IUndirectedMoveStrategy {

    public DirectionVector nextMoveDirection(Point start, GameField field) {
        DirectionVector dv = getRandomDirection();
        return field.pointIsOutOfBorders(start.x + dv.dx, start.y + dv.dy) ? DirectionVector.zero() : dv;
    }

    private DirectionVector getRandomDirection() {
        Random generator = new Random();
        if(generator.nextInt(2) == 0) {
            int dx = generator.nextInt(2) == 0 ? -1 : 1;
            return new DirectionVector(dx, 0);
        } else {
            int dy = generator.nextInt(2) == 0 ? -1 : 1;
            return new DirectionVector(0, dy);
        }
    }
}
