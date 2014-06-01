package ru.drunkard.field;

import ru.drunkard.fieldobjects.IFieldObj;
import ru.drunkard.game.RectGamePrinter;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicField implements GameField {
    protected List<IFieldObj> objects = new ArrayList<>();
    protected List<IFieldObj> lastAdded = new ArrayList<>();
    protected List<List<Sector>> sectors;

    @Override
    public void makeAllObjectsDoActions() {
        for(IFieldObj obj : objects) {
            obj.doActions(this);
        }
        refreshObjectsList();
    }

    @Override
    public void setObjectInSector(int x, int y, IFieldObj object) {
        sectors.get(y).get(x).setObject(object);
    }

    @Override
    public void addNewObject(IFieldObj newObject) {
        lastAdded.add(newObject);
    }

    @Override
    public boolean sectorIsEmpty(int x, int y) {
        return sectors.get(y).get(x).isEmpty();
    }

    @Override
    public void sendVisitorToSector(int x, int y, IFieldObj visitor) {
        sectors.get(y).get(x).acceptVisitor(visitor);
    }

    @Override
    public void sendVisitorToSector(int x, int y, RectGamePrinter visitor) {
        sectors.get(y).get(x).acceptVisitor(visitor);
    }

    private void refreshObjectsList() {
        if(!lastAdded.isEmpty()) {
            objects.addAll(lastAdded);
            lastAdded.clear();
        }
    }
}
