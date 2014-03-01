package ru.drunkard.fieldobjects;

/**
 * Created by mr_ito on 2/28/14.
 */
public class Column implements IPassiveObj {

    public void doActions(FieldState fieldState) {
        // does nothing
    }

    public void accept(Drunkard drunkard) {
        drunkard.sendToSleep();
    }
}
