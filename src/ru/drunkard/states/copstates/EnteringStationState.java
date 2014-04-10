package ru.drunkard.states.copstates;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.Cop;

/**
 * Created by mr_ito on 4/10/14.
 */
public class EnteringStationState implements ICopsState {
    public void doActions(Cop cop, Field field) {
        cop.EnterTheStation(field);
    }
}
