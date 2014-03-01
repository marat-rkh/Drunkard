package ru.drunkard.field;

import ru.drunkard.fieldobjects.IFieldObj;
import ru.drunkard.game.GamePrinter;

/**
 * Created by mr_ito on 2/28/14.
 */
public class Sector {
    private IFieldObj object;

    Sector(IFieldObj object) {
        this.object = object;
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

    public void acceptVisitor(GamePrinter visitor) {
        object.accept(visitor);
    }
}
