package ru.drunkard.field;

import ru.drunkard.fieldobjects.IFieldObj;
import ru.drunkard.game.RectGamePrinter;
import ru.drunkard.utility.Point;

import java.util.Iterator;
import java.util.List;

public interface GameField {
    public void makeAllObjectsDoActions();
    public void setObjectInSector(int x, int y, IFieldObj object);
    public void addNewObject(IFieldObj newObject);
    public boolean sectorIsEmpty(int x, int y);
    public void sendVisitorToSector(int x, int y, IFieldObj visitor);
    public void sendVisitorToSector(int x, int y, RectGamePrinter visitor);

    public boolean pointIsOutOfBorders(int x, int y);
    public List<Point> getNeighbours(Point p, Point special);
    public Iterator<Point> getIterator(Point topleft, Point bottomright);
    public Iterator<Point> getIterator();
}
