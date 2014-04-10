package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.game.GamePrinter;

/**
 * Created by mr_ito on 4/9/14.
 */
public class LampPost implements IFieldObj {
    public final int LIGHT_AREA_RADIUS = 3;

    public void doActions(Field field) { /*does nothing*/ }

    public void accept(IFieldObj visitor) { visitor.visit(this); }
    public void visit(Drunkard drunkard) {}
    public void visit(Column column) {}
    public void visit(Bottle bottle) {}
    public void visit(LampPost lampPost) {}
    public void visit(Cop cop) {}

    public void accept(GamePrinter printer) { printer.visit(this); }
}
