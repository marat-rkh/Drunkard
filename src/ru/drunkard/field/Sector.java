package ru.drunkard.field;

import ru.drunkard.fieldobjects.IFieldObj;

/**
 * Created by mr_ito on 2/28/14.
 */
public class Sector {

    private int x;
    private int y;
    private IFieldObj object;

    Sector(int x, int y, IFieldObj object) {
        this.x = x;
        this.y = y;
        this.object = object;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEmpty() {
        return object == null;
    }

    public void setObject(IFieldObj object) {
        this.object = object;
    }

    public void acceptVisitor(IFieldObj visitor) {
        object.accept(visitor);
    }
}
