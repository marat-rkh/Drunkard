package ru.drunkard.states.movableobjstate;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.ISeekerWithState;

public interface ISeekerState {
    public void initActions(ISeekerWithState sws, Field field);
}
