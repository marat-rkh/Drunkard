package ru.drunkard.states.copstates;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.Cop;

public class SearchForDrunkardState implements ICopsState {
    public void doActions(Cop cop, Field field) {
        cop.SearchForDrunkard(field);
    }
}
