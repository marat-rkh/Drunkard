package ru.drunkard.fieldobjects;

import ru.drunkard.field.FieldState;

/**
 * Created by mr_ito on 2/28/14.
 */
public interface IFieldObj {

    public void doActions(FieldState fieldState);

    public void accept(IFieldObj visitor);
    public void visit(Drunkard drunkard);
    public void visit(Column column);
    public void visit(Bottle bottle);
}
