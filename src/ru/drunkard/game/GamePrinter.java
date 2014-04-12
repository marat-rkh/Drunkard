package ru.drunkard.game;

import ru.drunkard.field.Field;
import ru.drunkard.fieldobjects.*;
import ru.drunkard.utility.Point;

import java.util.ArrayList;
import java.util.List;

public class GamePrinter {
    private List<Character> frameUpperPart;
    private List<Character> frameBottomPart;
    private List<Character> frameRightPart;
    private List<Character> frameLeftPart;

    private final char FRAME_EMPTY_ELEM = ' ';
    private final char EMPTY = '.';
    private final char TAVERN = 'T';
    private final char POLICE_STATION = 'P';
    private final char GLASS_STATION = 'G';
    private final String HORIZONTAL_DELIMITER = "  ";

    public GamePrinter(int fieldWidth, int fieldHeight) {
        frameUpperPart = new ArrayList<>();
        initFramePart(frameUpperPart, fieldWidth + 2);
        frameBottomPart = new ArrayList<>();
        initFramePart(frameBottomPart, fieldWidth + 2);
        frameRightPart = new ArrayList<>();
        initFramePart(frameRightPart, fieldHeight);
        frameLeftPart = new ArrayList<>();
        initFramePart(frameLeftPart, fieldHeight);
    }

    public void setTavern(Point pos) { setFrameObject(pos, TAVERN); }
    public void setPoliceStation(Point pos) { setFrameObject(pos, POLICE_STATION); }
    public void setGlassStation(Point pos) { setFrameObject(pos, GLASS_STATION); }

    public void printField(Integer gameStepNo, Field gameField) {
        System.out.println("Game step : " + gameStepNo.toString());
        for(Character ch : frameUpperPart) {
            printChar(ch);
        }
        System.out.println();
        for(int i = 0; i < gameField.getHeight(); i++) {
            printChar(frameLeftPart.get(i));
            for(int j = 0; j < gameField.getWidth(); j++) {
                if(gameField.sectorIsEmpty(j, i)) {
                    printChar(EMPTY);
                } else {
                    gameField.sendVisitorToSector(j, i, this);
                }
            }
            System.out.println(frameRightPart.get(i));
        }
        for(Character ch : frameBottomPart) {
            printChar(ch);
        }
        System.out.println();
    }

    private void initFramePart(List<Character> framePart, int size) {
        for(int i = 0; i < size; i++) {
            framePart.add(FRAME_EMPTY_ELEM);
        }
    }

    private void setFrameObject(Point pos, char symbol) {
        if(pos.y == 0) {
            frameUpperPart.add(pos.x + 1, symbol);
        } else if(pos.y == frameLeftPart.size() - 1) {
            frameBottomPart.add(pos.x + 1, symbol);
        } else if(pos.x == 0) {
            frameLeftPart.add(pos.y, symbol);
        } else {
            frameRightPart.add(pos.y, symbol);
        }
    }
    private void printChar(char symbol) {
        System.out.print(symbol);
        System.out.print(HORIZONTAL_DELIMITER);
    }

    public void visit(Drunkard drunkard) {
        if(drunkard.isSleeping) {
            printChar('z');
        } else if(drunkard.isFallen) {
            printChar('&');
        } else {
            printChar('D');
        }
    }
    public void visit(Bottle bottle) { printChar('b' ); }
    public void visit(Column column) { printChar('C'); }
    public void visit(LampPost lampPost) { printChar('L'); }
    public void visit(Cop cop) { printChar('P'); }
    public void visit(Hobo hobo) { printChar('H'); }
}
