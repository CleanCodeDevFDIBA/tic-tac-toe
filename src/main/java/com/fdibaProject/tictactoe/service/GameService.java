package com.fdibaProject.tictactoe.service;

import com.fdibaProject.tictactoe.exceptions.InvalidGameException;
import com.fdibaProject.tictactoe.exceptions.InvalidParamException;
import com.fdibaProject.tictactoe.exceptions.NotFoundException;
import com.fdibaProject.tictactoe.models.*;
import com.fdibaProject.tictactoe.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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

    public Game gamePlay(GamePlay gamePlay) throws NotFoundException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())){
            String message = String.format("Game with id %s cannot be found!", gamePlay.getGameId());
            throw new NotFoundException(message);
        }

        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());
        if (game.getStatus().equals(GameStatus.FINISHED)){
            throw new InvalidGameException("Game is already finished!");
        }

        int [][] board = game.getBoard();
        board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()] = gamePlay.getType().getValue();

        Boolean hasWonX = checkWinCondition(board, Signs.X);
        Boolean hasWonO = checkWinCondition(board, Signs.O);
        if (hasWonX || hasWonO){
            game.setStatus(GameStatus.FINISHED);
            if (hasWonX) {
                game.setWinner(Signs.X);
            }
            else {
                game.setWinner(Signs.O);
            }
        }

        GameStorage.getInstance().setGame(game);
        return game;
    }

    public boolean checkWinCondition(int [][] board, Signs sign) {
        int[] boardAsArray = new int[9];
        int counterIndex = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boardAsArray[counterIndex] = board[i][j];
                counterIndex++;
            }
        }

        int[][] winCombos = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}
        };

        for (int i = 0; i < winCombos.length; i++) {
            int counterOfHits = 0;
            for (int j = 0; j < winCombos[i].length; j++) {
                if (boardAsArray[j] == sign.getValue()) {
                    counterOfHits++;
                }
                if (counterOfHits == 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
