package ru.drunkard.states.copstates;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.Cop;

public interface ICopsState {
    public void doActions(Cop cop, Field field);
}
