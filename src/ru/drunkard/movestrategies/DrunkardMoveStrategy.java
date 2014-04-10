package ru.drunkard.movestrategies;

import ru.drunkard.field.Field;
import ru.drunkard.utility.DirectionVector;
import ru.drunkard.utility.Point;

import java.util.Random;

public class DrunkardMoveStrategy implements IUndirectedMoveStrategy {

    public DirectionVector nextMoveDirection(Point start, Field field) {
        DirectionVector dv = getRandomDirection();
        return outOfBorders(start.x, start.y, dv, field) ? DirectionVector.zero() : dv;
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

    private boolean outOfBorders(int x, int y, DirectionVector directionVector, Field field) {
        int new_x = x + directionVector.dx;
        int new_y = y + directionVector.dy;
        return new_x >= field.getWidth() || new_y >= field.getHeight() || new_x < 0 || new_y < 0;
    }
}
