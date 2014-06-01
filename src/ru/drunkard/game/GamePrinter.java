package ru.drunkard.game;

import ru.drunkard.field.GameField;
import ru.drunkard.utility.Point;

public interface GamePrinter {
    public void print(Integer gameStepNo, GameField gameField);
    public void setTavern(Point pos);
    public void setPoliceStation(Point pos);
    public void setGlassStation(Point pos);
    public void setLampPost(Point lightAreaStart, Point lightAreaEnd);
}
