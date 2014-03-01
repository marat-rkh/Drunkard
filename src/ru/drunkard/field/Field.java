package ru.drunkard.field;

import ru.drunkard.fieldobjects.IFieldObj;
import ru.drunkard.game.GamePrinter;

import java.util.ArrayList;

/**
 * Created by mr_ito on 2/28/14.
 */
public class Field {

    private ArrayList<ArrayList<Sector>> sectors;
    private int height;
    private int width;

    public Field(int height, int width) {
        sectors = new ArrayList<ArrayList<Sector>>(height);
        for(int i = 0; i < height; i++) {
            ArrayList<Sector> row = new ArrayList<Sector>(width);
            for(int j = 0; j < width; i++) {
                row.set(j, new Sector(i, j, null));
            }
            sectors.set(i, row);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setObjectInSector(int x, int y, IFieldObj object) {
        sectors.get(x).get(y).setObject(object);
    }

    public boolean sectorIsEmpty(int x, int y) {
        return sectors.get(x).get(y).isEmpty();
    }

    public void sendVisitorToSector(int x, int y, IFieldObj visitor) {
        sectors.get(x).get(y).acceptVisitor(visitor);
    }

    public void sendVisitorToSector(int x, int y, GamePrinter visitor) {
        sectors.get(x).get(y).acceptVisitor(visitor);
    }
}
