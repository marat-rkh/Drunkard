package ru.drunkard.states.movableobjstate;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.ISeekerWithState;

public class SeekingState implements ISeekerState {
    public void initActions(ISeekerWithState sws, Field field) {
        sws.seekTarget(field);
    }
}
