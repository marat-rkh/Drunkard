package ru.drunkard.game;

public class DrunkardTheGame {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.startGame(2000, 400, 100);
    }
}
