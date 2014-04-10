package ru.drunkard.states.copstates;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.Cop;

/**
 * Created by mr_ito on 4/9/14.
 */
public class GoBackState implements ICopsState {
    public void doActions(Cop cop, Field field) {
        cop.ReturnToStation(field);
    }
}
