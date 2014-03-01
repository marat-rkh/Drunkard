package ru.drunkard.game;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.Bottle;
import ru.drunkard.fieldobjects.Column;
import ru.drunkard.fieldobjects.Drunkard;

/**
 * Created by mr_ito on 3/1/14.
 */
public class GamePrinter {

    private final String HORIZONTAL_DELIMITER = "  ";

    public void printField(int gameStepNo, Field gameField) {
        System.out.println("Game step #: " + String.valueOf(gameStepNo));
        for(int i = 0; i < gameField.getWidth(); i++) {
            for(int j = 0; j < gameField.getHeight(); j++) {
                if(gameField.sectorIsEmpty(i, j)) {
                    System.out.print(".");
                } else {
                    gameField.sendVisitorToSector(i, j, this);
                }
                System.out.print(HORIZONTAL_DELIMITER);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void visit(Drunkard drunkard) {
        if(drunkard.isSleeping) {
            System.out.print("Z");
        } else if(drunkard.isFallen) {
            System.out.print("&");
        } else {
            System.out.print("D");
        }
    }

    public void visit(Bottle bottle) {
        System.out.print("B");
    }

    public void visit(Column column) {
        System.out.print("C");
    }
}
