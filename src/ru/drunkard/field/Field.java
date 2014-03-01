package ru.drunkard.field;

import ru.drunkard.fieldobjects.IFieldObj;
import ru.drunkard.game.GamePrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by mr_ito on 2/28/14.
 */
public class Field {

    private ArrayList<IFieldObj> objects = new ArrayList<IFieldObj>();
    private ArrayList<IFieldObj> lastAdded = new ArrayList<IFieldObj>();
    private ArrayList<ArrayList<Sector>> sectors;
    private int width;
    private int height;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        sectors = new ArrayList<ArrayList<Sector>>();
        for(int i = 0; i < width; i++) {
            ArrayList<Sector> row = new ArrayList<Sector>();
            for(int j = 0; j < height; j++) {
                row.add(new Sector(i, j, null));
            }
            sectors.add(row);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void makeAllObjectsDoActions() {
        for(IFieldObj obj : objects) {
            obj.doActions(this);
        }
        refreshObjectsList();
    }

    public void setObjectInSector(int x, int y, IFieldObj object) {
        sectors.get(x).get(y).setObject(object);
    }

    public void addNewObject(IFieldObj newObject) {
        lastAdded.add(newObject);
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

    private void refreshObjectsList() {
        if(!lastAdded.isEmpty()) {
            objects.addAll(lastAdded);
            lastAdded.clear();
        }
    }
}
