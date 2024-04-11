package com.fdibaProject.tictactoe.service;

import com.fdibaProject.tictactoe.exceptions.InvalidGameException;
import com.fdibaProject.tictactoe.exceptions.InvalidParamException;
import com.fdibaProject.tictactoe.exceptions.NotFoundException;
import com.fdibaProject.tictactoe.models.Game;
import com.fdibaProject.tictactoe.models.GameStatus;
import com.fdibaProject.tictactoe.models.Player;
import com.fdibaProject.tictactoe.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(GameStatus.NEW);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(Player player2, String gameId) throws InvalidParamException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gameId)){
            String message = String.format("Game with id %s does not exist!", gameId);
            throw new InvalidParamException(message);
        }
        Game game = GameStorage.getInstance().getGames().get(gameId);

        if (game.getPlayer2() != null){
            String message = "Game is not valid anymore.";
            throw new InvalidGameException(message);
        }

        game.setPlayer2(player2);
        game.setStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToRandomGame (Player player2) throws NotFoundException {
        Game game = GameStorage.getInstance().getGames().values().stream()
                .filter(g->g.getStatus().equals(GameStatus.NEW))
                .findFirst().orElseThrow(()-> new NotFoundException("Game cannot be found!"));

        game.setPlayer2(player2);
        game.setStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }
}
