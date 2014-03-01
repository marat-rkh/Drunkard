package ru.drunkard.field;

import ru.drunkard.fieldobjects.IFieldObj;

import java.util.ArrayList;

/**
 * Created by mr_ito on 2/28/14.
 */
public class Field {

    private ArrayList<IFieldObj> objects = new ArrayList<IFieldObj>();
    private FieldState fieldState;

    public Field(int height, int width) {
        fieldState = new FieldState(height, width);
    }

    public void performActions() {
         for(int i = 0; i < objects.size(); i++) {
             objects.get(i).doActions(fieldState);
         }
    }
}