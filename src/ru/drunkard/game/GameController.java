package ru.drunkard.game;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.Column;
import ru.drunkard.fieldobjects.Drunkard;

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

    private Field gameField = new Field(FIELD_WIDTH, FIELD_HEIGHT);
    private int gameStepNumber = 0;

    public GameController() {
        Column column = new Column();
        gameField.setObjectInSector(COLUMN_X, COLUMN_Y, column);
        gameField.addNewObject(column);
    }

    public void startGame(int delay) {
        GamePrinter printer = new GamePrinter();
        while(true) {
            makeGameStep();
            ++gameStepNumber;
            printer.printField(gameStepNumber, gameField, TAVERN_X, TAVERN_Y);
            try {
                Thread.sleep(delay);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void makeGameStep() {
        gameField.makeAllObjectsDoActions();
        if(gameStepNumber % DRUNKARD_GENERATION_STEP == 0) {
            tryGenerateDrunkard();
        }
    }

    private void tryGenerateDrunkard() {
        if(gameField.sectorIsEmpty(TAVERN_X, TAVERN_Y)) {
            Drunkard newDrunkard = new Drunkard(TAVERN_X, TAVERN_Y);
            gameField.setObjectInSector(TAVERN_X, TAVERN_Y, newDrunkard);
            gameField.addNewObject(newDrunkard);
        }
    }
}
