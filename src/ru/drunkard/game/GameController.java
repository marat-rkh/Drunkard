package ru.drunkard.game;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.Column;
import ru.drunkard.fieldobjects.Cop;
import ru.drunkard.fieldobjects.Drunkard;
import ru.drunkard.fieldobjects.LampPost;
import ru.drunkard.movestrategies.DrunkardMoveStrategy;
import ru.drunkard.movestrategies.IDirectedMoveStrategy;
import ru.drunkard.movestrategies.ShortestPathMoveStrategy;
import ru.drunkard.utility.Point;
import ru.drunkard.utility.WatchingArea;

public class GameController {
    private final int FIELD_HEIGHT = 15;
    private final int FIELD_WIDTH = 15;
    private final int COLUMN_X = 7;
    private final int COLUMN_Y = 7;
    private final int LAMPPOST_X = 10;
    private final int LAMPPOST_Y = 3;
    private final int LAMPPOST_LIGHT_AREA_RADIUS = 3;
    private final int TAVERN_X = 9;
    private final int TAVERN_Y = 0;
    private final int POLICE_STATION_X = 14;
    private final int POLICE_STATION_Y = 3;
    private final int DRUNKARD_GENERATION_STEP = 20;

    private Field gameField = new Field(FIELD_WIDTH, FIELD_HEIGHT);
    private int gameStepNumber = 0;

    public GameController() {
        Column column = new Column();
        gameField.setObjectInSector(COLUMN_X, COLUMN_Y, column);
        gameField.addNewObject(column);
        LampPost lampPost = new LampPost();
        gameField.setObjectInSector(LAMPPOST_X, LAMPPOST_Y, lampPost);
        gameField.addNewObject(lampPost);
        gameField.addNewObject(createCop());
        //debug
        //debugCreateInitialObjects();
    }

    public void startGame(int maxSteps, int delay) {
        GamePrinter printer = new GamePrinter();
        for(; gameStepNumber < maxSteps; gameStepNumber++) {
            makeGameStep();
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
            Drunkard newDrunkard = new Drunkard(TAVERN_X, TAVERN_Y, new DrunkardMoveStrategy());
            gameField.setObjectInSector(TAVERN_X, TAVERN_Y, newDrunkard);
            gameField.addNewObject(newDrunkard);
        }
    }

    private Cop createCop() {
        Point startPos = new Point(POLICE_STATION_X, POLICE_STATION_Y);
        IDirectedMoveStrategy copsMoveStrategy = new ShortestPathMoveStrategy();
        Point lampPostPos = new Point(LAMPPOST_X, LAMPPOST_Y);
        WatchingArea watchingArea = new WatchingArea(gameField, lampPostPos, LAMPPOST_LIGHT_AREA_RADIUS);
        return new Cop(startPos, copsMoveStrategy, watchingArea);
    }

    private void debugCreateInitialObjects() {
        Drunkard newDrunkard = new Drunkard(14, 4, new DrunkardMoveStrategy());
        gameField.setObjectInSector(14, 4, newDrunkard);
        gameField.addNewObject(newDrunkard);
        Drunkard newDrunkard2 = new Drunkard(14, 2, new DrunkardMoveStrategy());
        gameField.setObjectInSector(14, 2, newDrunkard2);
        gameField.addNewObject(newDrunkard2);
        Drunkard newDrunkard3 = new Drunkard(12, 3, new DrunkardMoveStrategy());
        gameField.setObjectInSector(12, 3, newDrunkard3);
        gameField.addNewObject(newDrunkard3);
        Drunkard newDrunkard4 = new Drunkard(13, 4, new DrunkardMoveStrategy());
        gameField.setObjectInSector(13, 4, newDrunkard4);
        gameField.addNewObject(newDrunkard4);
        Drunkard newDrunkard5 = new Drunkard(13, 3, new DrunkardMoveStrategy());
        gameField.setObjectInSector(13, 3, newDrunkard5);
        gameField.addNewObject(newDrunkard5);
        Drunkard newDrunkard6 = new Drunkard(12, 4, new DrunkardMoveStrategy());
        gameField.setObjectInSector(12, 4, newDrunkard6);
        gameField.addNewObject(newDrunkard6);
        Drunkard newDrunkard7 = new Drunkard(12, 2, new DrunkardMoveStrategy());
        gameField.setObjectInSector(12, 2, newDrunkard7);
        gameField.addNewObject(newDrunkard7);
        Drunkard newDrunkard8 = new Drunkard(11, 3, new DrunkardMoveStrategy());
        gameField.setObjectInSector(11, 3, newDrunkard8);
        gameField.addNewObject(newDrunkard8);
        Drunkard newDrunkard9 = new Drunkard(10, 4, new DrunkardMoveStrategy());
        gameField.setObjectInSector(10, 4, newDrunkard9);
        gameField.addNewObject(newDrunkard9);
    }
}
