package com.fdibaProject.tictactoe.factory;

import com.fdibaProject.tictactoe.entities.Game;

public class GameFactory {

    private GameFactory() {
    }

    public static Game createGame() {
        Game game = new Game();
        game.setId(1L);
        game.setPlayer1("Player1");
        game.setPlayer2("Player2");
        game.setWinner("Player1");

        return game;

    }

}
