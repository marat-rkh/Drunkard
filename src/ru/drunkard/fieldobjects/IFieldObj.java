package ru.drunkard.fieldobjects;

/**
 * Created by mr_ito on 2/28/14.
 */
public interface IFieldObj {

    public void doActions(FieldState fieldState);

    public void accept(Drunkard drunkard);
}
