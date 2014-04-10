package ru.drunkard.fieldobjects;

import ru.drunkard.field.Field;
import ru.drunkard.game.GamePrinter;

public interface IFieldObj {

    public void doActions(Field field);

    public void accept(IFieldObj visitor);
    public void visit(Drunkard drunkard);
    public void visit(Column column);
    public void visit(Bottle bottle);
    public void visit(LampPost lampPost);
    public void visit(Cop cop);

    public void accept(GamePrinter printer);
}
