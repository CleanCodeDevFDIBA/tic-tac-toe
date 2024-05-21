package com.fdibaProject.tictactoe.factory;

import com.fdibaProject.tictactoe.entities.Game;
import com.fdibaProject.tictactoe.entities.Player;

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

    /*
    public static Player createPlayer(String name, int wins) {
        Player player = new Player(wins, name);
        player.setRanking(1);  // Assuming initial rank is set to 1

        return player;
    }
    */


}
