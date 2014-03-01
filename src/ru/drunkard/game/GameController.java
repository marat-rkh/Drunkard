package ru.drunkard.game;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.Column;
import ru.drunkard.fieldobjects.Drunkard;
import ru.drunkard.fieldobjects.IFieldObj;

import java.util.ArrayList;

/**
 * Created by mr_ito on 3/1/14.
 */
public class GameController {

    private final int FIELD_HEIGHT = 15;
    private final int FIELD_WIDTH = 15;
    private final int COLUMN_X = 7;
    private final int COLUMN_Y = 7;
    private final int TAVERN_X = 9;
    private final int TAVERN_Y = 0;
    private final int DRUNKARD_GENERATION_STEP = 20;

    private ArrayList<IFieldObj> objects = new ArrayList<IFieldObj>();
    private Field gameField = new Field(FIELD_HEIGHT, FIELD_WIDTH);
    private int gameStepNumber = 0;

    public GameController() {
        Column column = new Column();
        objects.add(column);
        gameField.setObjectInSector(COLUMN_X, COLUMN_Y, column);
    }

    public void startGame(int delay) {
        GamePrinter printer = new GamePrinter();
        while(objects.size() != FIELD_HEIGHT * FIELD_WIDTH) {
            makeGameStep();
            ++gameStepNumber;
            printer.printField(gameStepNumber, gameField);
            try {
                Thread.sleep(delay);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void makeGameStep() {
        if(gameStepNumber % DRUNKARD_GENERATION_STEP == 0) {
            tryGenerateDrunkard();
        }
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).doActions(gameField);
        }
    }

    private void tryGenerateDrunkard() {
        if(gameField.sectorIsEmpty(TAVERN_X, TAVERN_Y)) {
            Drunkard newDrunkard = new Drunkard();
            objects.add(newDrunkard);
            gameField.setObjectInSector(TAVERN_X, TAVERN_Y, newDrunkard);
        }
    }
}
