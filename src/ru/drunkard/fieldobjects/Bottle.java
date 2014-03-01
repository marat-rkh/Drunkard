package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.game.GamePrinter;

/**
 * Created by mr_ito on 2/28/14.
 */
public class Bottle implements INotMovableObj {

    public void doActions(Field field) {
        //does nothing
    }

    public void accept(IFieldObj visitor) {
        visitor.visit(this);
    }

    public void visit(Drunkard drunkard) {}
    public void visit(Column column) {}
    public void visit(Bottle bottle) {}

    public void accept(GamePrinter printer) {
        printer.visit(this);
    }
}
