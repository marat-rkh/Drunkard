package ru.drunkard.game;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.*;

public class GamePrinter {

    private final String HORIZONTAL_DELIMITER = "  ";
    private String LEFT_BORDER = "";

    public void printField(int gameStepNo, Field gameField, int tavern_x, int tavern_y) {
        System.out.println("Game step : " + String.valueOf(gameStepNo));
        if(tavern_y != 0) {
            LEFT_BORDER = " ";
        }
        if(tavern_x != 0) {
            System.out.print(LEFT_BORDER);
            for(int i = 0; i < tavern_x; i++) {
                System.out.print(" " + HORIZONTAL_DELIMITER);
            }
            System.out.print("T");
            System.out.println();
        }
        for(int i = 0; i < gameField.getHeight(); i++) {
            if(tavern_x == 0) {
                if(tavern_y == i){
                    System.out.print("T");
                } else {
                    System.out.print(LEFT_BORDER);
                }
            }
            for(int j = 0; j < gameField.getWidth(); j++) {
                if(gameField.sectorIsEmpty(j, i)) {
                    System.out.print(".");
                } else {
                    gameField.sendVisitorToSector(j, i, this);
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

    public void visit(Bottle bottle) { System.out.print("B"); }
    public void visit(Column column) { System.out.print("C"); }
    public void visit(LampPost lampPost) { System.out.print("L"); }
    public void visit(Cop cop) { System.out.print("P"); }
}
